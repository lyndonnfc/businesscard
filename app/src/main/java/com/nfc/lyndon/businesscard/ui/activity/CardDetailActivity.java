package com.nfc.lyndon.businesscard.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.present.DetailPresent;
import com.nfc.lyndon.businesscard.util.BitmapUtils;
import com.nfc.lyndon.businesscard.view.DetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 名片详情
 */
@RuntimePermissions
public class CardDetailActivity extends MvpActivity<DetailView, DetailPresent> {

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
    @BindView(R.id.tv_fax)
    TextView tvFax;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.lay_top)
    ConstraintLayout layTop;

    public static void startActivity(Context context, String id) {
        Intent intent = new Intent(context, CardDetailActivity.class);
        intent.putExtra("cardId", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        ButterKnife.bind(this);
    }

    @Override
    public DetailPresent initPresenter() {
        return new DetailPresent(mContext);
    }

    @OnClick({R.id.tv_share, R.id.tv_edit, R.id.tv_trans_nfc, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                CardDetailActivityPermissionsDispatcher.shareWithPermissionCheck(this, layTop);
                break;
            case R.id.tv_edit:
                presenter.toEdit();
                break;
            case R.id.tv_trans_nfc:
                presenter.toTransfer();
                break;
            case R.id.tv_delete:
                break;
        }
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    public void share(View v){
        Intent imageIntent = new Intent(Intent.ACTION_SEND);
        imageIntent.setType("image/jpeg");
        imageIntent.putExtra(Intent.EXTRA_STREAM,
                BitmapUtils.getUri(v, mContext));
        mContext.startActivity(Intent.createChooser(imageIntent, "分享"));
    }
}
