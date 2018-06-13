package com.nfc.lyndon.businesscard.ui.activity;

import android.os.Bundle;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.model.TransferModel;
import com.nfc.lyndon.businesscard.presenter.TransferPresenter;

/**
 * 正在传输页面
 */
public class TransferActivity extends MvpActivity<TransferPresenter, TransferModel>{

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
    protected TransferPresenter initPresenter() {
        return new TransferPresenter();
    }

    @Override
    protected TransferModel initModel() {
        return new TransferModel();
    }
}
