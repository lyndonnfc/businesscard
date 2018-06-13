package com.nfc.lyndon.businesscard.contract;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface EditContract {

    interface EditView extends BaseView {
        void showLoading();
        void hidLoading();
    }

    interface EditModel extends BaseModel {
        void createCard(long uid, String realName, String phone, String position, String department,
                        String companyName, String email, String address, StringCallback callback);
        void editCard(long uid, String realName, String phone, String position, String department,
                      String companyName, String email, String address, StringCallback callback);
    }

    abstract class EditPresenter extends BasePresenter<EditModel, EditView> {
        public abstract void createCard(long uid, String realName, String phone, String position,
                                        String department, String companyName, String email, String address);
        public abstract void editCard(long uid, String realName, String phone, String position,
                                      String department, String companyName, String email, String address);
    }
}
