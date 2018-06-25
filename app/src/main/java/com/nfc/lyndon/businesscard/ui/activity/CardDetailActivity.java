package com.nfc.lyndon.businesscard.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.contract.DetailContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.DetailModel;
import com.nfc.lyndon.businesscard.presenter.DetailPresenter;
import com.nfc.lyndon.businesscard.util.BitmapUtils;
import com.nfc.lyndon.businesscard.util.GlideRoundTransform;
import com.nfc.lyndon.businesscard.util.ScreenUtils;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.widget.ConfirmOrCancelDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 名片详情
 */
@RuntimePermissions
public class CardDetailActivity extends MvpActivity<DetailPresenter, DetailModel> implements
        DetailContract.DetailView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.lay_top)
    LinearLayout layTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_card)
    PhotoView ivCard;

    private long id;

    private CardEntity cardEntity;

    public static void startActivity(Context context, long id) {
        Intent intent = new Intent(context, CardDetailActivity.class);
        intent.putExtra("cardId", id);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        tvTitle.setText("名片详情");
        tvRight.setText("分享");
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

    @OnClick({R.id.iv_back, R.id.tv_edit, R.id.tv_right, R.id.tv_trans_nfc, R.id.tv_delete,
            R.id.tv_phone, R.id.tv_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
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
                NfcManager manager = (NfcManager) getSystemService(Context.NFC_SERVICE);
                NfcAdapter nfcAdapter;
                if (manager != null) {
                    nfcAdapter = manager.getDefaultAdapter();
                } else {
                    ToastUtils.toastShort("此手机不支持NFC功能");
                    return;
                }
                if (nfcAdapter == null || !nfcAdapter.isEnabled()) {
                    ToastUtils.toastShort("请打开NFC");
                    return;
                }
                if (cardEntity != null) {
                    String content = JSON.toJSONString(cardEntity);
                    mPresenter.toTransfer(content);
                }
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
            case R.id.tv_phone:
                CardDetailActivityPermissionsDispatcher
                        .dialPhoneWithPermissionCheck(this, cardEntity.getPhone());
                break;
            case R.id.tv_email:
                sendEMail(cardEntity.getEmail());
                break;
        }
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    public void share(View v) {
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
                .load(cardEntity.getCardUrl())
                .apply(new RequestOptions()
                        .fitCenter()
                        .transform(new GlideRoundTransform(this, 6,
                                GlideRoundTransform.CornerType.ALL)))
                .into(ivCard);
        tvName.setText(cardEntity.getRealName());
        tvPosition.setText(cardEntity.getPosition());
        tvCompany.setText(cardEntity.getCompanyName());
        tvPhone.setText(cardEntity.getPhone());
        tvDepartment.setText(cardEntity.getDepartment());
        tvEmail.setText(cardEntity.getEmail());
        tvAddress.setText(cardEntity.getAddress());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestDetail(id);
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    public void dialPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    /**
     * 发送邮件
     *
     * @param mailAdress
     */
    public void sendEMail(String mailAdress) {
        Uri uri = Uri.parse("mailto:" + mailAdress);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra(Intent.EXTRA_EMAIL, PreferenceManager.getInstance()
                .getString(PreferenceManager.EMAIL));
        startActivity(Intent.createChooser(intent, "请选择发送方式"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

}
