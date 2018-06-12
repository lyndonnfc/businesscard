package com.nfc.lyndon.businesscard.app;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.lzy.okgo.OkGo;

public class BCApplication extends Application {

    public static BCApplication instance;

    @Override
    public void onCreate() {

        super.onCreate();

        if (Build.VERSION.SDK_INT >= 24) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }

        instance = this;
        OkGo.getInstance().init(this);
    }

    public static BCApplication getInstance() {
        return instance;
    }
}
