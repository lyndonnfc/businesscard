package com.nfc.lyndon.businesscard.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.app.Constants;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.contract.EditContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.EditModel;
import com.nfc.lyndon.businesscard.presenter.EditPresenter;
import com.nfc.lyndon.businesscard.util.AppUtils;
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
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_font)
    PhotoView ivFont;
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
    private boolean isCreate;

    private long cardId;

    private boolean isSelf;

    private ProgressDialog dialog;

    private String logo;

    private CardEntity cardEntity;

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
    }

    @Override
    public void initView() {
        tvTitle.setText("编辑名片");
        tvRight.setText("完成");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isCreate = bundle.getBoolean("isCreate");
            isSelf = bundle.getBoolean("isSelf");
            if (!isCreate) {
                cardId = bundle.getLong("cardId");
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

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.iv_font, R.id.lay_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_font:
            case R.id.lay_head:
                EditActivityPermissionsDispatcher.takePhotoWithPermissionCheck(this);
                break;
            case R.id.tv_right:
                String realName = etName.getText().toString();
                String company = etCompany.getText().toString();
                String department = etDepartment.getText().toString();
                String position = etPosition.getText().toString();
                String mobile = etMobile.getText().toString();
                String email = etEmail.getText().toString();
                String address = etAddress.getText().toString();

                if (TextUtils.isEmpty(realName)) {
                    ToastUtils.toastShort("请输入姓名");
                    return;
                }

                if (isCreate) {
                    mPresenter.createCard(PreferenceManager.getInstance().getLong(PreferenceManager.UID),
                            isSelf, logo, realName, mobile, position, department, company, email, address);
                } else {
                    mPresenter.editCard(cardId, PreferenceManager.getInstance().getLong(PreferenceManager.UID),
                            logo, realName, mobile, position, department, company, email, address);
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
        }
    }

    @Override
    public void showLogo(String imgUrl) {
        logo = imgUrl;
        Glide.with(mContext)
                .load(imgUrl)
                .into(ivFont);
    }

    @Override
    public void showLoading(String message) {
        dialog = new ProgressDialog(mContext, R.style.transparent_dialog);
        dialog.show();
        dialog.setMessage(message);
    }

    @Override
    public void hidLoading() {
        dialog.dismiss();
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
                        mPresenter.uploadLogo(file);
                    }
                    break;
            }
        }
    }
}
