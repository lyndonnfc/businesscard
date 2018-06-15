package com.nfc.lyndon.businesscard.model;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.contract.CardListContract;
import com.nfc.lyndon.businesscard.manager.HttpManager;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CardModel implements CardListContract.CardListModel{

    @Override
    public void requestCardList(long uid, String keyword, StringCallback callback) {
        HttpManager.getInstance().getCardList(uid, keyword, callback);
    }

    @Override
    public void uploadCardFile(File file, StringCallback callback) {
        HttpManager.getInstance().uploadCardFile(file, callback);
    }
}
