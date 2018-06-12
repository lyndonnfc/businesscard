package com.nfc.lyndon.businesscard.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.contract.EditContract;

public class EditPresent extends EditContract.EditPresenter {

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
