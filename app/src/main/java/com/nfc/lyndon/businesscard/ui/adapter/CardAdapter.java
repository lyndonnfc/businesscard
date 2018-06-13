package com.nfc.lyndon.businesscard.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.model.CardModel;

import java.util.List;

public class CardAdapter extends BaseQuickAdapter<CardEntity, BaseViewHolder> {

    public CardAdapter(int layoutResId, @Nullable List<CardEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardEntity item) {
        Glide.with(mContext)
                .load(item.getCompanyName())
                .apply(new RequestOptions().circleCrop().error(R.drawable.default_avatar))
                .into((ImageView) helper.getView(R.id.iv_avatar));

        helper.setText(R.id.tv_name, item.getRealName());
        helper.setText(R.id.tv_company, item.getCompanyName());
        helper.setText(R.id.tv_mobile, item.getPhone());
    }
}
