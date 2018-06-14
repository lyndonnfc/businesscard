package com.nfc.lyndon.businesscard.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseFragment;
import com.nfc.lyndon.businesscard.contract.CardListContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.manager.PreferenceManager;
import com.nfc.lyndon.businesscard.model.CardModel;
import com.nfc.lyndon.businesscard.presenter.CardListPresenter;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.ui.adapter.CardAdapter;
import com.nfc.lyndon.businesscard.util.ScreenUtils;
import com.nfc.lyndon.businesscard.widget.PictureSelectorDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 名片列表
 */
public class CardListFragment extends BaseFragment<CardListPresenter, CardModel> implements
        BaseQuickAdapter.OnItemClickListener, CardListContract.CardListView,
        PictureSelectorDialog.OnDialogClickListener{

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

    private String keyword = "";

    public CardAdapter mAdapter;

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
        initAdapter();
        mAdapter.setOnItemClickListener(this);
        rvList.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.requestCardList(PreferenceManager.getInstance().getLong(PreferenceManager.UID), keyword);
    }

    public void initAdapter() {
        View headView = new View(mContext);
        headView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtils.dip2px(mContext, 20)));
        View footerView = new View(mContext);
        footerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtils.dip2px(mContext, 20)));
        mAdapter = new CardAdapter(R.layout.item_card, new ArrayList<CardEntity>());
        mAdapter.addHeaderView(headView);
        mAdapter.addFooterView(footerView);
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
                keyword = etSearch.getText().toString().trim();
                mPresenter.requestCardList(PreferenceManager.getInstance().getLong(PreferenceManager.UID), keyword);
                break;
            case R.id.iv_camera:
                PictureSelectorDialog dialog = new PictureSelectorDialog(mContext, R.style.transparent_dialog);
                dialog.setOnDialogClickListener(this);
                dialog.show();
                break;
            case R.id.iv_input:
                Bundle bundle = new Bundle();
                bundle.putBoolean("isCreate", true);
                EditActivity.startActivity(mContext, bundle);
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

    @Override
    public void showAddView() {
        layAddCard.setVisibility(View.VISIBLE);
        rvList.setVisibility(View.GONE);
    }

    @Override
    public void updateView(List<CardEntity> data) {
        layAddCard.setVisibility(View.GONE);
        rvList.setVisibility(View.VISIBLE);
        mAdapter.setNewData(data);
    }

    @Override
    public void camera() {

    }

    @Override
    public void gallery() {

    }
}
