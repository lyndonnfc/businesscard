package com.nfc.lyndon.businesscard.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseActivity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PreferenceManager.getInstance().getBoolean(PreferenceManager.IS_LOGIN)) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 1500);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }
}
