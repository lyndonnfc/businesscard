package com.nfc.lyndon.businesscard.contract;

import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;
import com.nfc.lyndon.businesscard.model.UserModel;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface CardListContract {

    interface CardListView extends BaseView {

    }

    interface CardListModel extends BaseModel {
        List<UserModel> getData();
    }

    abstract class CardListPresenter extends BasePresenter<CardListContract.CardListModel,
            CardListContract.CardListView> {
    }
}
