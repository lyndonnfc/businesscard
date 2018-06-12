package com.nfc.lyndon.businesscard.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseFragment;
import com.nfc.lyndon.businesscard.model.UserModel;
import com.nfc.lyndon.businesscard.presenter.CardListPresent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 名片列表
 */
public class CardListFragment extends BaseFragment<CardListPresent, UserModel> implements BaseQuickAdapter.OnItemClickListener{

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.iv_input)
    ImageView ivInput;
    @BindView(R.id.lay_add_card)
    ConstraintLayout layAddCard;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        layAddCard.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvList.setLayoutManager(linearLayoutManager);
        mPresenter.initAdapter();
        mPresenter.mAdapter.setOnItemClickListener(this);
        rvList.setAdapter(mPresenter.mAdapter);

        mPresenter.update();
        return rootView;
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_card_list;
    }

    @Override
    protected CardListPresent initPresenter() {
        return new CardListPresent(mContext);
    }

    @Override
    protected UserModel initModel() {
        return new UserModel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_search, R.id.iv_camera, R.id.iv_input})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                break;
            case R.id.iv_camera:
                break;
            case R.id.iv_input:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(adapter, view, position);
    }
}
