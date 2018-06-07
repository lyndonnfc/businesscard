package com.nfc.lyndon.businesscard.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseActivity;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
