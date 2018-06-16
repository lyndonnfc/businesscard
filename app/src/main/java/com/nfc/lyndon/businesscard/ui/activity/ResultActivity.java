package com.nfc.lyndon.businesscard.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.model.ResultModel;
import com.nfc.lyndon.businesscard.presenter.ResultPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 结果页面
 */
public class ResultActivity extends MvpActivity<ResultPresenter, ResultModel> {

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
    @BindView(R.id.et_department)
    EditText etDepartment;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    private CardEntity cardEntity = new CardEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            cardEntity = (CardEntity) bundle.getSerializable("cardInfo");
            showData();
        }
    }

    private void showData() {
        if (cardEntity!=null){
            etName.setText(cardEntity.getRealName());
            etCompany.setText(cardEntity.getCompanyName());
            etMobile.setText(cardEntity.getPhone());
            etDepartment.setText(cardEntity.getDepartment());
            etEmail.setText(cardEntity.getEmail());
            etPosition.setText(cardEntity.getPosition());
            etAddress.setText(cardEntity.getAddress());
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_result;
    }

    @Override
    protected ResultPresenter initPresenter() {
        return new ResultPresenter();
    }

    @Override
    protected ResultModel initModel() {
        return new ResultModel();
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
