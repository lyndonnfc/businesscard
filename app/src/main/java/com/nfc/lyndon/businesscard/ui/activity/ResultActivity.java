package com.nfc.lyndon.businesscard.ui.activity;

import android.os.Bundle;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.model.ResultModel;
import com.nfc.lyndon.businesscard.presenter.ResultPresent;

/**
 * 结果页面
 */
public class ResultActivity extends MvpActivity<ResultPresent, ResultModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_result;
    }

    @Override
    protected ResultPresent initPresenter() {
        return new ResultPresent();
    }

    @Override
    protected ResultModel initModel() {
        return new ResultModel();
    }

}
