package com.nfc.lyndon.businesscard.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.nfc.lyndon.businesscard.app.BCApplication;

/**
 * 数据持久化管理
 */
public class PreferenceManager {

    private static volatile PreferenceManager mInstance = null;

    public static final String IS_LOGIN = "is_login";

    public static final String UID = "uid";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private PreferenceManager(){
        sharedPreferences = BCApplication.getInstance()
                .getSharedPreferences("business", Context.MODE_PRIVATE);
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

    public void setString(String key, String value){
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key){
        return  sharedPreferences.getString(key, "");
    }

    public void setBoolean(String key, boolean value){
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public Boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }

    public void setLong(String key, long value){
        editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key){
        return  sharedPreferences.getLong(key, 0);
    }

}
