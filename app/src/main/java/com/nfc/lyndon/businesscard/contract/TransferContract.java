package com.nfc.lyndon.businesscard.contract;

import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;

/**
 * Created by Administrator on 2018/6/12.
 */
public interface TransferContract {

    interface TransferView extends BaseView {
    }

    interface TransferModel extends BaseModel {
    }

    abstract class TransferPresenter extends BasePresenter<TransferModel, TransferView> {
    }
}
