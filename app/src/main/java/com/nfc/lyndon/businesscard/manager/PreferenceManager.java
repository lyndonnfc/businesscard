package com.nfc.lyndon.businesscard.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.nfc.lyndon.businesscard.app.BCApplication;

/**
 * 数据持久化管理
 */
public class PreferenceManager {

    private static volatile PreferenceManager mInstance = null;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private PreferenceManager(){
        sharedPreferences = BCApplication.getInstance()
                .getSharedPreferences("mb_car", Context.MODE_PRIVATE);
    }

    public static synchronized PreferenceManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new PreferenceManager();
                }
            }
        }
        return mInstance;
    }

}
