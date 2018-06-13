package com.nfc.lyndon.businesscard.contract;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;

public interface LoginContract {

    interface LoginView extends BaseView{

        void startCountDown();

        void endCountDown();
    }

    interface LoginModel extends BaseModel{
        void requestCode(String phone, StringCallback callback);

        void login(String code, String phone, StringCallback callback);
    }

    abstract class LoginPresenter extends BasePresenter<LoginModel, LoginView>{

        public abstract void requestCode(String phone);

        public abstract void login(String code, String phone);
    }
}
