package com.nfc.lyndon.businesscard.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.contract.DetailContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.DetailModel;
import com.nfc.lyndon.businesscard.presenter.DetailPresenter;
import com.nfc.lyndon.businesscard.util.BitmapUtils;
import com.nfc.lyndon.businesscard.widget.ConfirmOrCancelDialog;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 名片详情
 */
@RuntimePermissions
public class CardDetailActivity extends MvpActivity<DetailPresenter, DetailModel> implements
        DetailContract.DetailView{

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_company_en)
    TextView tvCompanyEn;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.lay_top)
    ConstraintLayout layTop;

    private long id;

    private CardEntity cardEntity;

    public static void startActivity(Context context, long id) {
        Intent intent = new Intent(context, CardDetailActivity.class);
        intent.putExtra("cardId", id);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        id = getIntent().getLongExtra("cardId", 0);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_card_detail;
    }

    @Override
    protected DetailPresenter initPresenter() {
        return new DetailPresenter(mContext);
    }

    @Override
    protected DetailModel initModel() {
        return new DetailModel();
    }

    @OnClick({R.id.tv_share, R.id.tv_edit, R.id.tv_trans_nfc, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                CardDetailActivityPermissionsDispatcher.shareWithPermissionCheck(this, layTop);
                break;
            case R.id.tv_edit:
                Bundle bundle = new Bundle();
                bundle.putBoolean("isCreate", false);
                bundle.putLong("cardId", id);
                bundle.putSerializable("cardInfo", cardEntity);
                mPresenter.toEdit(bundle);
                break;
            case R.id.tv_trans_nfc:
                mPresenter.toTransfer();
                break;
            case R.id.tv_delete:
                ConfirmOrCancelDialog dialog = new ConfirmOrCancelDialog(mContext,
                        R.style.transparent_dialog);
                dialog.setOnDialogClickListener(new ConfirmOrCancelDialog.OnDialogClickListener() {
                    @Override
                    public void confirm() {
                        mPresenter.deleteCard(id, PreferenceManager.getInstance().getLong(PreferenceManager.UID));
                    }
                });
                dialog.show();
                dialog.setMessage("确定要删除此名片吗");
                break;
        }
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    public void share(View v){
        Intent imageIntent = new Intent(Intent.ACTION_SEND);
        imageIntent.setType("image/jpeg");
        imageIntent.putExtra(Intent.EXTRA_STREAM,
                BitmapUtils.getUri(v, this));
        startActivity(Intent.createChooser(imageIntent, "分享"));
    }

    @Override
    public void showLoading(String message) {
        showDialog(message);
    }

    @Override
    public void hidLoading() {
        hidDialog();

    }

    @Override
    public void showData(CardEntity cardEntity) {
        this.cardEntity = new CardEntity();
        this.cardEntity = cardEntity;
        Glide.with(mContext)
                .load(cardEntity.getLogo())
                .apply(new RequestOptions().circleCrop())
                .into(ivAvatar);
        tvName.setText(cardEntity.getRealName());
        tvPosition.setText(cardEntity.getPosition());
        tvCompany.setText(cardEntity.getCompanyName());
        tvMobile.setText(cardEntity.getPhone());
        tvDepartment.setText(cardEntity.getDepartment());
        tvEmail.setText(cardEntity.getEmail());
        tvAddress.setText(cardEntity.getAddress());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestDetail(id);
    }
}
