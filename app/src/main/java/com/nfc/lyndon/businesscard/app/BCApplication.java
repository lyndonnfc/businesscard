package com.nfc.lyndon.businesscard.app;

import android.app.Application;

import com.lzy.okgo.OkGo;

public class BCApplication extends Application {

    public static BCApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        OkGo.getInstance().init(this);
    }

    public static BCApplication getInstance() {
        return instance;
    }
}
