package com.nfc.lyndon.businesscard.contract;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;
import com.nfc.lyndon.businesscard.entity.CardEntity;

/**
 * Created by Administrator on 2018/6/12.
 */
public interface SendCardContract {

    interface SendCardView extends BaseView {
        void showData(CardEntity cardEntity);

        void failed(String message);
    }

    interface SendCardModel extends BaseModel {
        void requestDetail(long uid, StringCallback callback);
    }

    abstract class SendCardPresenter extends BasePresenter<SendCardModel, SendCardView> {

        public abstract void requestDetail(long uid);
    }
}
