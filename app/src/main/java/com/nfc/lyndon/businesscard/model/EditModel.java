package com.nfc.lyndon.businesscard.model;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.contract.EditContract;

/**
 * Created by Administrator on 2018/6/12.
 */

public class EditModel implements EditContract.EditModel {

    @Override
    public void createCard(long uid, String realName, String phone, String position,
                           String department, String companyName, String email, String address,
                           StringCallback callback) {

    }

    @Override
    public void editCard(long uid, String realName, String phone, String position,
                         String department, String companyName, String email, String address,
                         StringCallback callback) {

    }
}
