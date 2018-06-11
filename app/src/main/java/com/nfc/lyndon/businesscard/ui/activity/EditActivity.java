package com.nfc.lyndon.businesscard.ui.activity;

import android.os.Bundle;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.present.EditPresent;
import com.nfc.lyndon.businesscard.view.EditView;

/**
 * 编辑页面
 */
public class EditActivity extends MvpActivity<EditView, EditPresent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    @Override
    public EditPresent initPresenter() {
        return new EditPresent();
    }
}
