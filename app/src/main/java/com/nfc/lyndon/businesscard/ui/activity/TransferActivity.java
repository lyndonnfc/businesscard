package com.nfc.lyndon.businesscard.ui.activity;

import android.os.Bundle;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.model.TransferModel;
import com.nfc.lyndon.businesscard.presenter.TransferPresent;

/**
 * 正在传输页面
 */
public class TransferActivity extends MvpActivity<TransferPresent, TransferModel>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_transfering;
    }

    @Override
    protected TransferPresent initPresenter() {
        return new TransferPresent();
    }

    @Override
    protected TransferModel initModel() {
        return new TransferModel();
    }
}
