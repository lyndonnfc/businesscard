package com.nfc.lyndon.businesscard.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.nfc.lyndon.businesscard.app.Constants;
import com.nfc.lyndon.businesscard.base.BaseResponse;
import com.nfc.lyndon.businesscard.contract.LoginContract;
import com.nfc.lyndon.businesscard.entity.UserEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.util.WeakHandler;

public class LoginPresenter extends LoginContract.LoginPresenter{

    private static final int LOGIN_SUCCESS = 1;

    private WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case LOGIN_SUCCESS:
                    BaseResponse baseResponse = (BaseResponse) msg.obj;
                    UserEntity entity = (UserEntity) baseResponse.getResult();
                    PreferenceManager.getInstance().setLong(PreferenceManager.UID, entity.getUid());
                    PreferenceManager.getInstance().setBoolean(PreferenceManager.IS_LOGIN, true);
                    ToastUtils.toastShort(baseResponse.getMsg());
                    getView().toMain();
                    break;
            }
            return false;
        }
    });

    @Override
    public void requestCode(String phone) {
        final LoginContract.LoginView mView = getView();
        mModel.requestCode(phone, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseResponse baseResponse = JSON.parseObject(response.body(), BaseResponse.class);
                if (baseResponse.getStat() == Constants.SUCCESS){
                    mView.success(baseResponse.getMsg());
                } else {
                    mView.failed(baseResponse.getMsg());
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                mView.endCountDown();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                mView.startCountDown();
            }
        });
    }

    @Override
    public void login(String code, String phone) {
        final LoginContract.LoginView mView = getView();
        mModel.login(code, phone, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseResponse<UserEntity> baseResponse = JSON.parseObject(response.body(),
                        new TypeReference<BaseResponse<UserEntity>>(){});
                if (baseResponse != null && baseResponse.getStat() == Constants.SUCCESS){
                    message = new Message();
                    message.what = LOGIN_SUCCESS;
                    message.obj = baseResponse;
                    mHandler.sendMessage(message);
                } else {
                    if (baseResponse != null)
                        mView.failed(baseResponse.getMsg());
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                mView.failed(response.getException().getMessage());
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
            }
        });
    }
}
