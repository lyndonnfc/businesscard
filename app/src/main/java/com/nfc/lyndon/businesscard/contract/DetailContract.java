package com.nfc.lyndon.businesscard.contract;

import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface DetailContract {

    interface DetailView extends BaseView {

    }

    interface DetailModel extends BaseModel {
    }

    abstract class DetailPresenter extends BasePresenter<DetailContract.DetailModel,
            DetailContract.DetailView> {
    }
}
