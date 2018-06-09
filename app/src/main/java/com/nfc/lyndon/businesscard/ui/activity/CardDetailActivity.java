package com.nfc.lyndon.businesscard.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseActivity;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.present.DetailPresent;
import com.nfc.lyndon.businesscard.view.DetailView;

/**
 * 名片详情
 */
public class CardDetailActivity extends MvpActivity<DetailView, DetailPresent> {

    public static void startActivity(Context context, String id){
        Intent intent = new Intent(context, CardDetailActivity.class);
        intent.putExtra("cardId", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
    }

    @Override
    public DetailPresent initPresenter() {
        return new DetailPresent();
    }
}
