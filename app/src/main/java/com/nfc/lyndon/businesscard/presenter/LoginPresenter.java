package com.nfc.lyndon.businesscard.presenter;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.nfc.lyndon.businesscard.contract.LoginContract;

public class LoginPresenter extends LoginContract.LoginPresenter{

    @Override
    public void requestCode(String phone) {
        final LoginContract.LoginView mView = getView();
        mView.startCountDown();
        mModel.requestCode(phone, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

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
        mModel.login(code, phone, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
            }
        });
    }
}
