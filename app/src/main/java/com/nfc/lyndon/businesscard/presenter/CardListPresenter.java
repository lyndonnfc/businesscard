package com.nfc.lyndon.businesscard.presenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.contract.CardListContract;
import com.nfc.lyndon.businesscard.contract.LoginContract;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.model.CardModel;
import com.nfc.lyndon.businesscard.ui.activity.CardDetailActivity;
import com.nfc.lyndon.businesscard.ui.adapter.CardAdapter;
import com.nfc.lyndon.businesscard.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 名片列表presenter
 */
public class CardListPresenter extends CardListContract.CardListPresenter {

    public CardAdapter mAdapter;
    private List<CardEntity> mData;
    private Context mContext;

    public CardListPresenter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
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

    public void update() {
        mAdapter.setNewData(mData);
    }

    public void itemClick(int position) {
        CardDetailActivity.startActivity(mContext, mData.get(position).getId());
    }

    @Override
    public void requestCardList(long uid, String keyword) {
        final CardListContract.CardListView mView = getView();

        mModel.requestCardList(uid, keyword, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                mView.hidLoading();
                CardEntity entity;
                for (int i = 0; i < 10; i++) {
                    entity = new CardEntity();
                    entity.setId(i);
                    entity.setLogo("http://img.zcool.cn/community/01432f5958ecc1a8012193a375857c.jpg");
                    entity.setRealName("范冰冰");
                    entity.setPhone("18872211703");
                    entity.setCompanyName("深圳市腾讯计算机系统有限公司");
                    mData.add(entity);
                }
                update();
            }

        @Override
        public void onError (Response < String > response) {
            super.onError(response);
            mView.hidLoading();
            CardEntity entity;
            for (int i = 0; i < 10; i++) {
                entity = new CardEntity();
                entity.setLogo("http://img.zcool.cn/community/01432f5958ecc1a8012193a375857c.jpg");
                entity.setRealName("范冰冰");
                entity.setPhone("18872211703");
                entity.setCompanyName("深圳市腾讯计算机系统有限公司");
                mData.add(entity);
            }
            update();
        }

        @Override
        public void onStart (Request < String, ? extends Request > request){
            super.onStart(request);
            mView.showLoading();
        }
    });
}
}
