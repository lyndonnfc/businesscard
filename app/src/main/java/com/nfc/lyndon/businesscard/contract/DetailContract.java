package com.nfc.lyndon.businesscard.contract;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;
import com.nfc.lyndon.businesscard.entity.CardEntity;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface DetailContract {

    interface DetailView extends BaseView {
        void showLoading(String message);

        void hidLoading();

        void showData(CardEntity cardEntity);
    }

    interface DetailModel extends BaseModel {
        void requestDetail(long id, StringCallback callback);

        void deleteCard(long id, long uid, StringCallback callback);
    }

    abstract class DetailPresenter extends BasePresenter<DetailModel, DetailView> {
        public abstract void requestDetail(long id);

        public abstract void deleteCard(long id, long uid);
    }
}
