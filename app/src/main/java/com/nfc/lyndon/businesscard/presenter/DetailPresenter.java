package com.nfc.lyndon.businesscard.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.nfc.lyndon.businesscard.app.Constants;
import com.nfc.lyndon.businesscard.base.BaseResponse;
import com.nfc.lyndon.businesscard.contract.DetailContract;
import com.nfc.lyndon.businesscard.entity.CardDetailEntity;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.ui.activity.TransferActivity;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.util.WeakHandler;

public class DetailPresenter extends DetailContract.DetailPresenter{

    private static final int HANDLER_GET_DETAIL_SUCCESS = 1;

    private static final int HANDlER_DELETE_SUCCESS = 2;

    private static final int HANDLER_FAILED = 3;

    private Context mContext;

    private WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case HANDLER_GET_DETAIL_SUCCESS:
                    CardEntity cardEntity = (CardEntity) message.obj;
                    getView().showData(cardEntity);
                    break;
                case HANDlER_DELETE_SUCCESS:
                    ToastUtils.toastShort((String) message.obj);
                    ((Activity)mContext).finish();
                    break;
                case HANDLER_FAILED:
                    ToastUtils.toastShort((String) message.obj);
                    break;
            }
            return false;
        }
    });

    public DetailPresenter(Context context){
        this.mContext = context;
    }

    public void toEdit(Bundle bundle){
        EditActivity.startActivity(mContext, bundle);
    }

    public void toTransfer(String content){
        Intent intent = new Intent(mContext, TransferActivity.class);
        intent.putExtra("content", content);
        mContext.startActivity(intent);
    }

    @Override
    public void requestDetail(long id) {
        final DetailContract.DetailView mView = getView();

        mModel.requestDetail(id, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                mView.hidLoading();
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
                    if (baseResponse!=null){
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
                mView.hidLoading();
                message = new Message();
                message.what = HANDLER_FAILED;
                message.obj = response.getException().getMessage();
                mHandler.sendMessage(message);
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                mView.showLoading("");
            }
        });
    }

    @Override
    public void deleteCard(long id, long uid) {
        final DetailContract.DetailView mView = getView();

        mModel.deleteCard(id, uid, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                mView.hidLoading();
                BaseResponse baseResponse = JSON.parseObject(response.body(), new TypeReference<BaseResponse>(){});
                if (baseResponse != null && baseResponse.getStat() == Constants.SUCCESS){
                    message = new Message();
                    message.what = HANDlER_DELETE_SUCCESS;
                    message.obj = baseResponse.getMsg();
                    mHandler.sendMessage(message);
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
                mView.hidLoading();
                message = new Message();
                message.what = HANDLER_FAILED;
                message.obj = response.getException().getMessage();
                mHandler.sendMessage(message);
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                mView.showLoading("正在删除...");
            }
        });
    }
}
