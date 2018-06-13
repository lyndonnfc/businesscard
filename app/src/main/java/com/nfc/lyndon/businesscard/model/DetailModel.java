package com.nfc.lyndon.businesscard.model;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.contract.DetailContract;
import com.nfc.lyndon.businesscard.manager.HttpManager;

/**
 * Created by Administrator on 2018/6/12.
 */
public class DetailModel implements DetailContract.DetailModel {
    @Override
    public void requestDetail(long id, StringCallback callback) {
        HttpManager.getInstance().getCardDetail(id, callback);
    }

    @Override
    public void deleteCard(long id, StringCallback callback) {
        HttpManager.getInstance().deleteCard(id, callback);
    }
}
