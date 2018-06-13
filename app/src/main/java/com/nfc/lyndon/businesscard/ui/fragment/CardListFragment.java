package com.nfc.lyndon.businesscard.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseFragment;
import com.nfc.lyndon.businesscard.contract.CardListContract;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.CardModel;
import com.nfc.lyndon.businesscard.presenter.CardListPresenter;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 名片列表
 */
public class CardListFragment extends BaseFragment<CardListPresenter, CardModel> implements
        BaseQuickAdapter.OnItemClickListener, CardListContract.CardListView{

    private static final int REQUEST_CREATE_CARD = 1;

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

    private String keyword;

    @Override
    protected int getContentId() {
        return R.layout.fragment_card_list;
    }

    @Override
    protected void initView() {
        layAddCard.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvList.setLayoutManager(linearLayoutManager);
        mPresenter.initAdapter();
        mPresenter.mAdapter.setOnItemClickListener(this);
        rvList.setAdapter(mPresenter.mAdapter);

        keyword = etSearch.getText().toString().trim();
        mPresenter.requestCardList(PreferenceManager.getInstance().getLong(PreferenceManager.UID), keyword);
    }

    @Override
    protected CardListPresenter initPresenter() {
        return new CardListPresenter(mContext);
    }

    @Override
    protected CardModel initModel() {
        return new CardModel();
    }

    @OnClick({R.id.iv_search, R.id.iv_camera, R.id.iv_input})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                break;
            case R.id.iv_camera:
                break;
            case R.id.iv_input:
                EditActivity.startActivity(mContext, new Bundle());
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.itemClick(position);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hidLoading() {

    }
}
