package com.nfc.lyndon.businesscard.contract;

import com.nfc.lyndon.businesscard.base.BaseModel;
import com.nfc.lyndon.businesscard.base.BasePresenter;
import com.nfc.lyndon.businesscard.base.BaseView;

/**
 * Created by Administrator on 2018/6/12.
 */

public interface ResultContract {

    interface ResultView extends BaseView {

    }

    interface ResultModel extends BaseModel {
    }

    abstract class ResultPresenter extends BasePresenter<ResultModel, ResultView> {
    }
}
