package com.nfc.lyndon.businesscard.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.app.Constants;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.contract.EditContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.EditModel;
import com.nfc.lyndon.businesscard.presenter.EditPresenter;
import com.nfc.lyndon.businesscard.util.AppUtils;
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

    private boolean isCreate;

    private long cardId;

    private boolean isSelf;

    private String path;

    private String cPath;

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
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isCreate = bundle.getBoolean("isCreate");
            isSelf = bundle.getBoolean("isSelf");
            if (!isCreate){
                cardId = bundle.getLong("cardId");
            }
            cardEntity = (CardEntity) bundle.getSerializable("cardInfo");
        }
        Glide.with(mContext)
                .load(R.drawable.img_avatar_bg)
                .apply(new RequestOptions()
                .circleCrop())
                .into(ivFont);
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

    @OnClick({R.id.iv_font, R.id.btn_save, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_font:
                PictureSelectorDialog dialog = new PictureSelectorDialog(mContext, R.style.transparent_dialog);
                dialog.setOnDialogClickListener(this);
                dialog.show();
                break;
            case R.id.btn_save:
                String realName = etName.getText().toString();
                String company = etCompany.getText().toString();
                String department = etDepartment.getText().toString();
                String position = etPosition.getText().toString();
                String mobile = etMobile.getText().toString();
                String email = etEmail.getText().toString();
                String address = etAddress.getText().toString();

                if (TextUtils.isEmpty(logo)){
                    ToastUtils.toastShort("请先上传logo");
                    return;
                } else if (TextUtils.isEmpty(realName)){
                    ToastUtils.toastShort("请输入姓名");
                    return;
                } else if (TextUtils.isEmpty(company)){
                    ToastUtils.toastShort("请输入公司");
                    return;
                } else if (TextUtils.isEmpty(department)){
                    ToastUtils.toastShort("请输入部门");
                    return;
                } else if (TextUtils.isEmpty(position)){
                    ToastUtils.toastShort("请输入职位");
                    return;
                } else if (TextUtils.isEmpty(mobile)){
                    ToastUtils.toastShort("请输入电话");
                    return;
                } else if (TextUtils.isEmpty(email)){
                    ToastUtils.toastShort("请输入邮箱");
                    return;
                } else if (TextUtils.isEmpty(address)){
                    ToastUtils.toastShort("请输入地址");
                    return;
                }

                if (isCreate){
                    mPresenter.createCard(PreferenceManager.getInstance().getLong(PreferenceManager.UID),
                            isSelf, logo, realName, mobile, position, department, company, email, address);
                } else {
                    mPresenter.editCard(cardId, PreferenceManager.getInstance().getLong(PreferenceManager.UID),
                            logo, realName, mobile, position, department, company, email, address);
                }

                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    private void showData(){
        if (cardEntity != null){
            logo = cardEntity.getLogo();
            Glide.with(mContext)
                    .load(cardEntity.getLogo())
                    .apply(new RequestOptions().circleCrop())
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
                .apply(new RequestOptions().circleCrop())
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
        path = getExternalCacheDir() + File.separator + "temp.png";
        AppUtils.openCameraPage(mContext,this, path);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void album() {
        AppUtils.openAlbumPage(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constants.CAMERA_REQUEST_CODE:
                    cPath = getExternalCacheDir() + File.separator + "corp.png";
                    AppUtils.corp(mContext, Uri.fromFile(new File(path)), cPath);
                    break;
                case Constants.ALBUM_REQUEST_CODE:
                    cPath = getExternalCacheDir() + File.separator + "corp.png";
                    AppUtils.corp(mContext, data.getData(), cPath);
                    break;
                case Constants.CROP_REQUEST_CODE:
                    mPresenter.uploadLogo(new File(cPath));
                    break;
            }
        }
    }
}
