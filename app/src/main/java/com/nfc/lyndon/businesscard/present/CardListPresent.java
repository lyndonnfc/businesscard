package com.nfc.lyndon.businesscard.present;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BasePresent;
import com.nfc.lyndon.businesscard.model.UserModel;
import com.nfc.lyndon.businesscard.ui.activity.CardDetailActivity;
import com.nfc.lyndon.businesscard.ui.adapter.CardAdapter;
import com.nfc.lyndon.businesscard.util.ScreenUtils;
import com.nfc.lyndon.businesscard.view.CardListView;

import java.util.ArrayList;
import java.util.List;

public class CardListPresent extends BasePresent<CardListView>{

    public CardAdapter mAdapter;

    private List<UserModel> mData;

    private Context mContext;

    public CardListPresent(Context context){
        mContext = context;
        UserModel userModel = new UserModel();
        mData = new ArrayList<>();
        mData = userModel.getData();
    }

    public void initAdapter(){
        View headView = new View(mContext);
        headView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(mContext, 20)));
        View footerView = new View(mContext);
        footerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(mContext, 20)));
        mAdapter = new CardAdapter(R.layout.item_card, new ArrayList<UserModel>());
        mAdapter.addHeaderView(headView);
        mAdapter.addFooterView(footerView);
    }

    public void update(){
        mAdapter.setNewData(mData);
    }

    public void itemClick(BaseQuickAdapter adapter, View view, int position){
        CardDetailActivity.startActivity(mContext, mData.get(position).getUserId());
    }

}
