package com.nfc.lyndon.businesscard.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nfc.lyndon.businesscard.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity{

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mContext = this;
    }

    /**
     * 显示error图片
     * @param url 图片地址
     * @param imageView 控件
     */
    public void displayImage(String url, ImageView imageView) {
        Glide.with(getApplicationContext())//
                .load(url)//
                .apply(new RequestOptions().error(R.mipmap.ic_launcher))
                .into(imageView);
    }
}
