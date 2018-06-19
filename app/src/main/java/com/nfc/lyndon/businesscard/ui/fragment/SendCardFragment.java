package com.nfc.lyndon.businesscard.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseFragment;
import com.nfc.lyndon.businesscard.contract.SendCardContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.SendCardModel;
import com.nfc.lyndon.businesscard.presenter.SendCardPresenter;
import com.nfc.lyndon.businesscard.util.BitmapUtils;
import com.nfc.lyndon.businesscard.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 递名片页面
 */
@RuntimePermissions
public class SendCardFragment extends BaseFragment<SendCardPresenter, SendCardModel> implements
        SendCardContract.SendCardView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_company_en)
    TextView tvCompanyEn;
    @BindView(R.id.lay_base)
    RelativeLayout layBase;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_trans_nfc)
    TextView tvTransNfc;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.lay_create_card)
    ConstraintLayout layCreateCard;
    @BindView(R.id.tv_create)
    TextView tvCreate;
    @BindView(R.id.lay_create)
    RelativeLayout layCreate;
    @BindView(R.id.tv_create_desc)
    TextView tvCreateDesc;
    @BindView(R.id.lay_top)
    ConstraintLayout layTop;
    Unbinder unbinder;

    private CardEntity cardEntity;

    @Override
    protected int getContentId() {
        return R.layout.fragment_send_card;
    }

    @Override
    protected void initView() {
        ivAvatar.setVisibility(View.GONE);
        layBase.setVisibility(View.GONE);
        tvTransNfc.setVisibility(View.GONE);
        tvShare.setVisibility(View.GONE);
        tvEdit.setVisibility(View.GONE);
        layCreateCard.setVisibility(View.VISIBLE);
    }

    @Override
    protected SendCardPresenter initPresenter() {
        return new SendCardPresenter(mContext);
    }

    @Override
    protected SendCardModel initModel() {
        return new SendCardModel();
    }

    @OnClick({R.id.tv_trans_nfc, R.id.tv_share, R.id.tv_edit, R.id.lay_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_trans_nfc:
                NfcManager manager = (NfcManager) mContext.getSystemService(Context.NFC_SERVICE);
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
            case R.id.tv_share:
                SendCardFragmentPermissionsDispatcher.shareWithPermissionCheck(this, layTop);
                break;
            case R.id.tv_edit:
                Bundle bundle = new Bundle();
                bundle.putBoolean("isCreate", false);
                bundle.putLong("cardId", cardEntity.getId());
                bundle.putSerializable("cardInfo", cardEntity);
                mPresenter.toEdit(bundle);
                break;
            case R.id.lay_create:
                Bundle create_bundle = new Bundle();
                create_bundle.putBoolean("isCreate", true);
                create_bundle.putBoolean("isSelf", true);
                mPresenter.toEdit(create_bundle);
                break;
        }
    }

    @Override
    public void showData(CardEntity cardEntity) {
        this.cardEntity = new CardEntity();
        this.cardEntity = cardEntity;
        ivAvatar.setVisibility(View.VISIBLE);
        layBase.setVisibility(View.VISIBLE);
        tvTransNfc.setVisibility(View.VISIBLE);
        tvShare.setVisibility(View.VISIBLE);
        tvEdit.setVisibility(View.VISIBLE);
        layCreateCard.setVisibility(View.GONE);
        Glide.with(mContext)
                .load(cardEntity.getLogo())
                .apply(new RequestOptions().circleCrop())
                .into(ivAvatar);
        tvName.setText(cardEntity.getRealName());
        tvPosition.setText(cardEntity.getPosition());
        tvCompany.setText(cardEntity.getCompanyName());
        PreferenceManager.getInstance().setString(PreferenceManager.EMAIL,
                cardEntity.getEmail());
    }

    @Override
    public void failed(String message) {
        ivAvatar.setVisibility(View.GONE);
        layBase.setVisibility(View.GONE);
        tvTransNfc.setVisibility(View.GONE);
        tvShare.setVisibility(View.GONE);
        tvEdit.setVisibility(View.GONE);
        layCreateCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.requestDetail(PreferenceManager.getInstance().getLong(PreferenceManager.UID));
    }

    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE})
    public void share(View v) {
        Intent imageIntent = new Intent(Intent.ACTION_SEND);
        imageIntent.setType("image/jpeg");
        imageIntent.putExtra(Intent.EXTRA_STREAM,
                BitmapUtils.getUri(v, mContext));
        startActivity(Intent.createChooser(imageIntent, "分享"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
