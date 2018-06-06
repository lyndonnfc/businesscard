package com.nfc.lyndon.businesscard.manager;

import com.nfc.lyndon.businesscard.app.BCApplication;
import com.nfc.lyndon.businesscard.gen.DaoMaster;
import com.nfc.lyndon.businesscard.gen.DaoSession;

/**
 * 数据库
 */
public class GreenDaoManager {

    public static final String DB_NAME = "business.db";

    private DaoMaster mDaoMaster;

    private DaoSession mDaoSession;

    private static volatile GreenDaoManager mInstance = null;

    private GreenDaoManager() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new
                    DaoMaster.DevOpenHelper(BCApplication.getInstance(), DB_NAME);
            mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static synchronized GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
