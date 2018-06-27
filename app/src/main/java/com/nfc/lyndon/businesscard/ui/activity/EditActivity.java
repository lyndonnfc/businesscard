package com.nfc.lyndon.businesscard.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.contract.EditContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.EditModel;
import com.nfc.lyndon.businesscard.presenter.EditPresenter;
import com.nfc.lyndon.businesscard.util.AppUtils;
import com.nfc.lyndon.businesscard.util.BitmapUtils;
import com.nfc.lyndon.businesscard.util.CheckUtils;
import com.nfc.lyndon.businesscard.util.GlideRoundTransform;
import com.nfc.lyndon.businesscard.util.StringUtils;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.widget.PictureSelectorDialog;
import com.nfc.lyndon.businesscard.widget.ProgressDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 编辑页面
 */
@RuntimePermissions
public class EditActivity extends MvpActivity<EditPresenter, EditModel> implements EditContract.EditView,
        PictureSelectorDialog.OnDialogClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_font)
    PhotoView ivFont;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_position)
    EditText etPosition;
    @BindView(R.id.et_department)
    EditText etDepartment;
    @BindView(R.id.et_company)
    EditText etCompany;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_phone_tag)
    TextView tvPhoneTag;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_company_tag)
    TextView tvCompanyTag;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_email_tag)
    TextView tvEmailTag;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_address_tag)
    TextView tvAddressTag;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.lay_top)
    LinearLayout layTop;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    private boolean isCreate;

    private long cardId;

    private boolean isSelf;

    private ProgressDialog dialog;

    private String logo;

    private CardEntity cardEntity;

    private String realName;
    private String company;
    private String department;
    private String position;
    private String mobile;
    private String email;
    private String address;

    public static void startActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        showData();

        AppUtils.setTextBold(tvName, true);

        etName.addTextChangedListener(new EditTextWatcher(etName, tvName));
        etDepartment.addTextChangedListener(new EditTextWatcher(etDepartment, tvDepartment));
        etPosition.addTextChangedListener(new EditTextWatcher(etPosition, tvPosition));
        etMobile.addTextChangedListener(new EditTextWatcher(etMobile, tvPhoneTag, tvPhone));
        etCompany.addTextChangedListener(new EditTextWatcher(etCompany, tvCompanyTag, tvCompany));
        etEmail.addTextChangedListener(new EditTextWatcher(etEmail, tvEmailTag, tvEmail));
        etAddress.addTextChangedListener(new EditTextWatcher(etAddress, tvAddressTag, tvAddress));
    }

    @Override
    public void initView() {
        tvTitle.setText("编辑名片");
        ivRight.setImageResource(R.drawable.ic_camera);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isCreate = bundle.getBoolean("isCreate");
            isSelf = bundle.getBoolean("isSelf");
            if (!isCreate) {
                cardId = bundle.getLong("cardId");
                ivCamera.setVisibility(View.GONE);
                ivRight.setVisibility(View.VISIBLE);
            } else {
                ivCamera.setVisibility(View.VISIBLE);
                ivRight.setVisibility(View.GONE);
            }
            cardEntity = (CardEntity) bundle.getSerializable("cardInfo");
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_edit;
    }

    @Override
    protected EditPresenter initPresenter() {
        return new EditPresenter(mContext);
    }

    @Override
    protected EditModel initModel() {
        return new EditModel();
    }

    @OnClick({R.id.iv_back, R.id.iv_right, R.id.iv_font, R.id.lay_head, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_font:
            case R.id.lay_head:
            case R.id.iv_right:
                EditActivityPermissionsDispatcher.takePhotoWithPermissionCheck(this);
                break;
            case R.id.btn_save:
                realName = etName.getText().toString();
                company = etCompany.getText().toString();
                department = etDepartment.getText().toString();
                position = etPosition.getText().toString();
                mobile = etMobile.getText().toString();
                email = etEmail.getText().toString();
                address = etAddress.getText().toString();

                if (mPresenter.noChange(realName, mobile, position, department, company, email, address, cardEntity)) {
                    ToastUtils.toastShort("名片没有改变");
                    return;
                }
                if (TextUtils.isEmpty(realName)) {
                    ToastUtils.toastShort("请输入姓名");
                    return;
                } else if (!TextUtils.isEmpty(mobile) && !StringUtils.isMobileNo(mobile)){
                    ToastUtils.toastShort("请输入正确的手机");
                    return;
                } else if (!TextUtils.isEmpty(email) && !CheckUtils.isEmail(email)){
                    ToastUtils.toastShort("请输入正确的邮箱");
                    return;
                }

                if (TextUtils.isEmpty(logo))
                    EditActivityPermissionsDispatcher.shareWithPermissionCheck(this, layTop);
                else {
                    if (isCreate) {
                        mPresenter.createCard(PreferenceManager.getInstance().getLong(PreferenceManager.UID),
                                isSelf, logo, realName, mobile, position, department, company, email, address);
                    } else {
                        mPresenter.editCard(cardId, PreferenceManager.getInstance().getLong(PreferenceManager.UID),
                                logo, realName, mobile, position, department, company, email, address);
                    }
                }
                break;
        }
    }

    private void showData() {
        if (cardEntity != null) {
            logo = cardEntity.getCardUrl();
            Glide.with(mContext)
                    .load(cardEntity.getCardUrl())
                    .apply(new RequestOptions()
                            .fitCenter()
                            .transform(new GlideRoundTransform(this, 4,
                                    GlideRoundTransform.CornerType.ALL)))
                    .into(ivFont);
            etName.setText(cardEntity.getRealName());
            etCompany.setText(cardEntity.getCompanyName());
            etDepartment.setText(cardEntity.getDepartment());
            etPosition.setText(cardEntity.getPosition());
            etMobile.setText(cardEntity.getPhone());
            etEmail.setText(cardEntity.getEmail());
            etAddress.setText(cardEntity.getAddress());
            tvName.setText(cardEntity.getRealName());
            tvDepartment.setText(cardEntity.getDepartment());
            tvPosition.setText(cardEntity.getPosition());

            if (!TextUtils.isEmpty(cardEntity.getCompanyName())) {
                tvCompanyTag.setVisibility(View.VISIBLE);
                tvCompany.setText(cardEntity.getCompanyName());
            }
            if (!TextUtils.isEmpty(cardEntity.getPhone())) {
                tvPhoneTag.setVisibility(View.VISIBLE);
                tvPhone.setText(cardEntity.getPhone());
            }
            if (!TextUtils.isEmpty(cardEntity.getEmail())) {
                tvEmailTag.setVisibility(View.VISIBLE);
                tvEmail.setText(cardEntity.getEmail());
            }
            if (!TextUtils.isEmpty(cardEntity.getAddress())) {
                tvAddressTag.setVisibility(View.VISIBLE);
                tvAddress.setText(cardEntity.getAddress());
            }
        }
    }

    @Override
    public void showLogo(String imgUrl) {
        logo = imgUrl;
        ivCamera.setVisibility(View.GONE);
        ivRight.setVisibility(View.VISIBLE);

        if (isCreate) {
            mPresenter.createCard(PreferenceManager.getInstance().getLong(PreferenceManager.UID),
                    isSelf, logo, realName, mobile, position, department, company, email, address);
        } else {
            mPresenter.editCard(cardId, PreferenceManager.getInstance().getLong(PreferenceManager.UID),
                    logo, realName, mobile, position, department, company, email, address);
        }
    }

    @Override
    public void showLoading(String message) {
        dialog = new ProgressDialog(mContext, R.style.transparent_dialog);
        dialog.show();
        dialog.setMessage(message);
    }

    @Override
    public void hidLoading() {
        if (dialog != null)
            dialog.dismiss();
    }

    @Override
    public void showData(CardEntity cardEntity) {
        if (cardEntity != null) {
            logo = cardEntity.getCardUrl();
            Glide.with(mContext)
                    .load(cardEntity.getCardUrl())
                    .apply(new RequestOptions()
                            .fitCenter()
                            .transform(new GlideRoundTransform(this, 4,
                                    GlideRoundTransform.CornerType.ALL)))
                    .into(ivFont);
            etName.setText(cardEntity.getRealName());
            etCompany.setText(cardEntity.getCompanyName());
            etDepartment.setText(cardEntity.getDepartment());
            etPosition.setText(cardEntity.getPosition());
            etMobile.setText(cardEntity.getPhone());
            etEmail.setText(cardEntity.getEmail());
            etAddress.setText(cardEntity.getAddress());
        }
    }

    @Override
    public void camera() {
        EditActivityPermissionsDispatcher.takePhotoWithPermissionCheck(this);
    }

    @Override
    public void gallery() {
        EditActivityPermissionsDispatcher.albumWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void takePhoto() {
        CameraActivity.openCertificateCamera(this);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void album() {
        AppUtils.openAlbumPage(this);
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    public void share(View v) {
        mPresenter.uploadLogo(new File(BitmapUtils.getImageAbsolutePath(mContext, BitmapUtils.getUri(v, mContext))));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CameraActivity.REQUEST_CODE:
                    if (data != null) {
                        String path = data.getStringExtra("path");
                        File file = new File(path);
                        if (!file.exists()) {
                            ToastUtils.toastShort("文件不存在");
                            return;
                        }
                        mPresenter.uploadCardFile(file);
                    }
                    break;
            }
        }
    }

    /**
     * EditText 监听
     */
    class EditTextWatcher implements TextWatcher {

        private EditText editText;
        private TextView tvTitle;
        private TextView tvContent;

        private EditTextWatcher(EditText editText, TextView tvContent) {
            this.editText = editText;
            this.tvContent = tvContent;
            this.editText.addTextChangedListener(this);
        }

        private EditTextWatcher(EditText editText, TextView tvTitle, TextView tvContent) {
            this.editText = editText;
            this.tvTitle = tvTitle;
            this.tvContent = tvContent;
            this.editText.addTextChangedListener(this);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())) {
                if (tvTitle != null)
                    tvTitle.setVisibility(View.INVISIBLE);
                tvContent.setVisibility(View.INVISIBLE);
            } else {
                if (tvTitle != null)
                    tvTitle.setVisibility(View.VISIBLE);
                tvContent.setVisibility(View.VISIBLE);
                tvContent.setText(s.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (ivCamera.getVisibility() == View.VISIBLE){
                ivCamera.setVisibility(View.GONE);
                ivRight.setVisibility(View.VISIBLE);
            }
        }
    }
}
