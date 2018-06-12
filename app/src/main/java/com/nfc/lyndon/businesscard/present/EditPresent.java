package com.nfc.lyndon.businesscard.present;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BasePresent;
import com.nfc.lyndon.businesscard.view.EditView;

public class EditPresent extends BasePresent<EditView> {

    private Context mContext;

    public EditPresent(Context context){
        this.mContext = context;
    }

    public void initView(ImageView view){
        Glide.with(mContext)
                .load(R.drawable.img_avatar_bg)
                .apply(new RequestOptions().circleCrop())
                .into(view);
    }

}
