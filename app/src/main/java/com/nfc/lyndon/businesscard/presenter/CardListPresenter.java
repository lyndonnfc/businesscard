package com.nfc.lyndon.businesscard.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseResponse;
import com.nfc.lyndon.businesscard.contract.CardListContract;
import com.nfc.lyndon.businesscard.contract.LoginContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.entity.CardListEntity;
import com.nfc.lyndon.businesscard.model.CardModel;
import com.nfc.lyndon.businesscard.ui.activity.CardDetailActivity;
import com.nfc.lyndon.businesscard.ui.adapter.CardAdapter;
import com.nfc.lyndon.businesscard.util.ScreenUtils;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.util.WeakHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 名片列表presenter
 */
public class CardListPresenter extends CardListContract.CardListPresenter {

    private static final int HANDLER_GET_CARD_LIST_SUCCESS = 1;
    private static final int HANDLER_GET_CARD_LIST_FAILED = 2;

    private List<CardEntity> mData;
    private Context mContext;

    private WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_GET_CARD_LIST_SUCCESS:
                    BaseResponse<CardListEntity> baseResponse = (BaseResponse<CardListEntity>) msg.obj;
                    if (baseResponse.getResult() == null) {
                        getView().showAddView();
                    } else if (baseResponse.getResult() != null &&
                            baseResponse.getResult().getCardInfoList() != null) {
                        mData = baseResponse.getResult().getCardInfoList();
                        getView().updateView(baseResponse.getResult().getCardInfoList());
                    }
                    break;
                case HANDLER_GET_CARD_LIST_FAILED:
                    ToastUtils.toastShort((String) msg.obj);
                    break;
            }
            return false;
        }
    });

    public CardListPresenter(Context context) {
        mContext = context;
    }

    public void itemClick(int position) {
        CardDetailActivity.startActivity(mContext, mData.get(position).getId());
    }

    @Override
    public void requestCardList(long uid, String keyword) {
        final CardListContract.CardListView mView = getView();

        mModel.requestCardList(uid, keyword, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                mView.hidLoading();
                BaseResponse<CardListEntity> baseResponse = JSON.parseObject(response.body(),
                        new TypeReference<BaseResponse<CardListEntity>>() {
                        });
                if (baseResponse != null && baseResponse.getStat() == 1) {
                    message = new Message();
                    message.what = HANDLER_GET_CARD_LIST_SUCCESS;
                    message.obj = baseResponse;
                    mHandler.sendMessage(message);
                } else {
                    if (baseResponse != null) {
                        message = new Message();
                        message.what = HANDLER_GET_CARD_LIST_SUCCESS;
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
                message.what = HANDLER_GET_CARD_LIST_SUCCESS;
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
    public void uploadCardFile(File file) {
        mModel.uploadCardFile(file, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                getView().hidLoading();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                getView().hidLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                getView().showLoading("正在识别...");
            }
        });
    }
}
