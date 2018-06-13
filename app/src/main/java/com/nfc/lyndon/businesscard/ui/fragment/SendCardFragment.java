package com.nfc.lyndon.businesscard.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseFragment;
import com.nfc.lyndon.businesscard.model.SendCardModel;
import com.nfc.lyndon.businesscard.presenter.SendCardPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 递名片页面
 */
public class SendCardFragment extends BaseFragment<SendCardPresenter, SendCardModel> {

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

    @Override
    protected int getContentId() {
        return R.layout.fragment_send_card;
    }

    @Override
    protected void initView() {
        ivAvatar.setVisibility(View.VISIBLE);
        layBase.setVisibility(View.VISIBLE);
        tvTransNfc.setVisibility(View.VISIBLE);
        tvShare.setVisibility(View.VISIBLE);
        tvEdit.setVisibility(View.VISIBLE);
        layCreateCard.setVisibility(View.GONE);
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
                mPresenter.toEdit(new Bundle());
                break;
            case R.id.lay_create:
                mPresenter.toEdit(new Bundle());
                break;
        }
    }
}
