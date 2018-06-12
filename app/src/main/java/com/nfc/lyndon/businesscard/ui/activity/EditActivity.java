package com.nfc.lyndon.businesscard.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.model.EditModel;
import com.nfc.lyndon.businesscard.presenter.EditPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑页面
 */
public class EditActivity extends MvpActivity<EditPresent, EditModel> {

    @BindView(R.id.iv_font)
    ImageView ivFont;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_company)
    EditText etCompany;
    @BindView(R.id.et_position)
    EditText etPosition;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_fax)
    EditText etFax;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_address)
    EditText etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter.initView(ivFont);
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_edit;
    }

    @Override
    protected EditPresent initPresenter() {
        return new EditPresent(mContext);
    }

    @Override
    protected EditModel initModel() {
        return new EditModel();
    }

    @OnClick({R.id.iv_font, R.id.btn_save, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_font:
                break;
            case R.id.btn_save:
                break;
            case R.id.btn_cancel:
                break;
        }
    }
}
