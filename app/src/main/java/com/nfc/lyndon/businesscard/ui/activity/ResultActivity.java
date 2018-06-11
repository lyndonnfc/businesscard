package com.nfc.lyndon.businesscard.ui.activity;

import android.os.Bundle;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseActivity;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.present.ResultPresent;
import com.nfc.lyndon.businesscard.view.ResultView;

/**
 * 结果页面
 */
public class ResultActivity extends MvpActivity<ResultView, ResultPresent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    public ResultPresent initPresenter() {
        return new ResultPresent();
    }
}
