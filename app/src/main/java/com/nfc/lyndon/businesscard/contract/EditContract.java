package com.nfc.lyndon.businesscard.contract;

import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface EditContract {

    interface EditView extends BaseView {

    }

    interface EditModel extends BaseModel {
    }

    abstract class EditPresenter extends BasePresenter<EditContract.EditModel, EditContract.EditView> {
    }
}
