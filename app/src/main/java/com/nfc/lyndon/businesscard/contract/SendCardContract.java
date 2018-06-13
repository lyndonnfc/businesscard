package com.nfc.lyndon.businesscard.contract;

import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface SendCardContract {

    interface SendCardView extends BaseView {

    }

    interface SendCardModel extends BaseModel {
    }

    abstract class SendCardPresenter extends BasePresenter<SendCardModel, SendCardView> {
    }
}
