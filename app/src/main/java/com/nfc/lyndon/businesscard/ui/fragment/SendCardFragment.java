package com.nfc.lyndon.businesscard.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseFragment;
import com.nfc.lyndon.businesscard.contract.SendCardContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.SendCardModel;
import com.nfc.lyndon.businesscard.presenter.SendCardPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 递名片页面
 */
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
                mPresenter.toTransfer();
                break;
            case R.id.tv_share:
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
}
