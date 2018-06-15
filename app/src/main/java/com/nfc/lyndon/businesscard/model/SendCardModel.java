package com.nfc.lyndon.businesscard.model;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.contract.SendCardContract;
import com.nfc.lyndon.businesscard.manager.HttpManager;

/**
 * Created by Administrator on 2018/6/12.
 */

public class SendCardModel implements SendCardContract.SendCardModel {

    @Override
    public void requestDetail(long uid, StringCallback callback) {
        HttpManager.getInstance().getSelfCardDetail(uid, callback);
    }
}
