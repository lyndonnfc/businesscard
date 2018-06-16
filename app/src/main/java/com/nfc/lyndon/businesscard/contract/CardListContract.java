package com.nfc.lyndon.businesscard.contract;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;
import com.nfc.lyndon.businesscard.entity.CardDetailEntity;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.model.CardModel;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface CardListContract {

    interface CardListView extends BaseView {
        void showLoading(String message);

        void hidLoading();

        void showAddView();

        void updateView(List<CardEntity> data);
    }

    interface CardListModel extends BaseModel {
        void requestCardList(long uid, String keyword, StringCallback callback);

        void uploadCardFile(File file, StringCallback callback);
    }

    abstract class CardListPresenter extends BasePresenter<CardListModel, CardListView> {
        public abstract void requestCardList(long uid, String keyword);

        public abstract void uploadCardFile(File file);
    }
}
