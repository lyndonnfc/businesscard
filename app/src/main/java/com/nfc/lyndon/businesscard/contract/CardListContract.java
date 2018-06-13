package com.nfc.lyndon.businesscard.contract;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;
import com.nfc.lyndon.businesscard.model.CardModel;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface CardListContract {

    interface CardListView extends BaseView {
        void showLoading();

        void hidLoading();
    }

    interface CardListModel extends BaseModel {
        void requestCardList(long uid, String keyword, StringCallback callback);
    }

    abstract class CardListPresenter extends BasePresenter<CardListModel, CardListView> {
        public abstract void requestCardList(long uid, String keyword);
    }
}
