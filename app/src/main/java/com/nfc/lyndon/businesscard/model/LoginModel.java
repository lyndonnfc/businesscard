package com.nfc.lyndon.businesscard.model;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.contract.LoginContract;
import com.nfc.lyndon.businesscard.manager.HttpManager;

public class LoginModel implements LoginContract.LoginModel {

    @Override
    public void requestCode(String phone, StringCallback callback) {
        HttpManager.getInstance().getCode(phone, callback);
    }

    @Override
    public void login(String code, String phone, StringCallback callback) {
        HttpManager.getInstance().login(code, phone, callback);
    }
}
