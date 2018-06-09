package com.nfc.lyndon.businesscard.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseFragment;
import com.nfc.lyndon.businesscard.base.BasePresent;
import com.nfc.lyndon.businesscard.present.SendCardPresent;
import com.nfc.lyndon.businesscard.view.SendCardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 递名片页面
 */
public class SendCardFragment extends BaseFragment<SendCardView, SendCardPresent> {

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
    Unbinder unbinder;

    @Override
    public SendCardPresent initPresenter() {
        return new SendCardPresent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        ivAvatar.setVisibility(View.VISIBLE);
        layBase.setVisibility(View.VISIBLE);
        tvTransNfc.setVisibility(View.VISIBLE);
        tvShare.setVisibility(View.VISIBLE);
        tvEdit.setVisibility(View.VISIBLE);
        layCreateCard.setVisibility(View.GONE);
        return rootView;
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_send_card;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_trans_nfc, R.id.tv_share, R.id.tv_edit, R.id.lay_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_trans_nfc:
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_edit:
                break;
            case R.id.lay_create:
                break;
        }
    }
}
