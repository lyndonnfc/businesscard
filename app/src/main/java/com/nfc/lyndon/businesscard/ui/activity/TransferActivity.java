package com.nfc.lyndon.businesscard.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.present.TransferPresent;
import com.nfc.lyndon.businesscard.view.TransferView;

/**
 * 正在传输页面
 */
public class TransferActivity extends MvpActivity<TransferView, TransferPresent> implements TransferView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfering);
    }

    @Override
    public TransferPresent initPresenter() {
        return new TransferPresent();
    }
}
