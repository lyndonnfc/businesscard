package com.nfc.lyndon.businesscard.model;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.contract.EditContract;
import com.nfc.lyndon.businesscard.manager.HttpManager;

import java.io.File;

/**
 * Created by Administrator on 2018/6/12.
 */

public class EditModel implements EditContract.EditModel {

    @Override
    public void uploadLogo(File file, StringCallback callback) {
        HttpManager.getInstance().uploadAvatar(file, callback);
    }

    @Override
    public void uploadCardFile(File file, StringCallback callback) {
        HttpManager.getInstance().uploadCardFile(file, callback);
    }

    @Override
    public void createCard(long uid, boolean isSelf, String logo, String realName, String phone, String position,
                           String department, String companyName, String email, String address,
                           StringCallback callback) {
        HttpManager.getInstance().createCard(uid, isSelf, logo, realName, phone, position, department,
                companyName, email, address, callback);
    }

    @Override
    public void editCard(long id, long uid, String logo, String realName, String phone, String position,
                         String department, String companyName, String email, String address,
                         StringCallback callback) {
        HttpManager.getInstance().updateCard(id, uid, logo, realName, phone, position, department,
                companyName, email, address, callback);
    }
}
