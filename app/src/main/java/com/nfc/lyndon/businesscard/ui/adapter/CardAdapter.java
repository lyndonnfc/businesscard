package com.nfc.lyndon.businesscard.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.model.UserModel;

import java.util.List;

public class CardAdapter extends BaseQuickAdapter<UserModel, BaseViewHolder> {

    public CardAdapter(int layoutResId, @Nullable List<UserModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserModel item) {
        Glide.with(mContext)
                .load(item.getAvatar())
                .apply(new RequestOptions().circleCrop().error(R.drawable.default_avatar))
                .into((ImageView) helper.getView(R.id.iv_avatar));

        helper.setText(R.id.tv_name, item.getUsername());
        helper.setText(R.id.tv_company, item.getCompany());
        helper.setText(R.id.tv_mobile, item.getMobile());
    }
}
