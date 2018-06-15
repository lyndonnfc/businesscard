package com.nfc.lyndon.businesscard.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.nfc.lyndon.businesscard.app.Constants;
import com.nfc.lyndon.businesscard.base.BaseResponse;
import com.nfc.lyndon.businesscard.contract.SendCardContract;
import com.nfc.lyndon.businesscard.entity.CardDetailEntity;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.ui.activity.TransferActivity;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.util.WeakHandler;

public class SendCardPresenter extends SendCardContract.SendCardPresenter {

    private static final int HANDLER_GET_DETAIL_SUCCESS = 1;

    private static final int HANDLER_FAILED = 2;

    private Context mContext;

    private WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case HANDLER_GET_DETAIL_SUCCESS:
                    CardEntity cardEntity = (CardEntity) message.obj;
                    getView().showData(cardEntity);
                    break;
                case HANDLER_FAILED:
                    getView().failed((String) message.obj);
                    break;
            }
            return false;
        }
    });

    public SendCardPresenter(Context context){
        this.mContext = context;
    }

    /**
     * 编辑名片
     * @param bundle bundle
     */
    public void toEdit(Bundle bundle){
        EditActivity.startActivity(mContext, bundle);
    }

    /**
     * nfc递名片
     */
    public void toTransfer(){
        Intent intent = new Intent(mContext, TransferActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void requestDetail(long uid) {
        mModel.requestDetail(uid, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseResponse<CardDetailEntity> baseResponse = JSON.parseObject(response.body(),
                        new TypeReference<BaseResponse<CardDetailEntity>>(){});
                if (baseResponse != null && baseResponse.getStat() == Constants.SUCCESS){
                    if (baseResponse.getResult() != null && baseResponse.getResult().getCardInfo() != null){
                        message = new Message();
                        message.what = HANDLER_GET_DETAIL_SUCCESS;
                        message.obj = baseResponse.getResult().getCardInfo();
                        mHandler.sendMessage(message);
                    }
                } else {
                    if (baseResponse != null){
                        message = new Message();
                        message.what = HANDLER_FAILED;
                        message.obj = baseResponse.getMsg();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                message = new Message();
                message.what = HANDLER_FAILED;
                message.obj = response.getException().getMessage();
                mHandler.sendMessage(message);
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
            }
        });
    }
}
