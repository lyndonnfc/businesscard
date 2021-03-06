package com.nfc.lyndon.businesscard.presenter;

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
import com.nfc.lyndon.businesscard.contract.CardListContract;
import com.nfc.lyndon.businesscard.entity.CardDetailEntity;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.entity.CardListEntity;
import com.nfc.lyndon.businesscard.ui.activity.CardDetailActivity;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.util.WeakHandler;

import java.io.File;
import java.util.List;

/**
 * 名片列表presenter
 */
public class CardListPresenter extends CardListContract.CardListPresenter {

    private static final int HANDLER_GET_CARD_LIST_SUCCESS = 1;
    private static final int HANDLER_GET_CARD_LIST_FAILED = 2;
    private static final int HANDLER_GET_RESULT_SUCCESS = 3;
    private static final int HANDLER_FAILED = 4;

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
                    getView().hidLoading();
                    ToastUtils.toastShort((String) msg.obj);
                    break;
                case HANDLER_GET_RESULT_SUCCESS:
                    getView().hidLoading();
                    CardEntity cardEntity = (CardEntity) message.obj;
                    Intent intent = new Intent(mContext, EditActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isCreate", true);
                    bundle.putSerializable("cardInfo", cardEntity);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                    break;
                case HANDLER_FAILED:
                    getView().hidLoading();
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

        mModel.requestCardList(uid, keyword, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
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
                message.what = HANDLER_GET_CARD_LIST_FAILED;
                message.obj = response.getException().getMessage();
                mHandler.sendMessage(message);
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
            }
        });
    }

    @Override
    public void uploadCardFile(File file) {
        mModel.uploadCardFile(file, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                CardEntity cardEntity = new CardEntity();
                BaseResponse<CardDetailEntity> baseResponse = JSON.parseObject(response.body(),
                        new TypeReference<BaseResponse<CardDetailEntity>>() {
                        });
                if (cardEntity.noResult(response.body())) {
                    message = new Message();
                    message.what = HANDLER_FAILED;
                    message.obj = "识别没结果";
                    mHandler.sendMessage(message);
                } else {
                    if (baseResponse != null && baseResponse.getStat() == Constants.SUCCESS) {
                        if (baseResponse.getResult() != null &&
                                baseResponse.getResult().getNfcBusinessCardInfo() != null) {
                            message = new Message();
                            message.what = HANDLER_GET_RESULT_SUCCESS;
                            message.obj = baseResponse.getResult().getNfcBusinessCardInfo();
                            mHandler.sendMessage(message);
                        }
                    } else {
                        if (baseResponse != null) {
                            message = new Message();
                            message.what = HANDLER_FAILED;
                            message.obj = baseResponse.getMsg();
                            mHandler.sendMessage(message);
                        }
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
