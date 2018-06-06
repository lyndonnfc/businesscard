package com.nfc.lyndon.businesscard.manager;

/**
 * 网络请求管理
 */
public class HttpManager {

    private static volatile HttpManager mInstance = null;

    private HttpManager(){

    }

    public static synchronized HttpManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new HttpManager();
                }
            }
        }
        return mInstance;
    }
}
