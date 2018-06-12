package com.nfc.lyndon.businesscard.base;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jaeger.library.StatusBarUtil;
import com.nfc.lyndon.businesscard.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity{

    protected Context mContext;

    public static Typeface mTypeface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(getAssets(), "lfbsf.ttf");
        }

        LayoutInflaterCompat.setFactory2(LayoutInflater.from(mContext), new LayoutInflater.Factory2() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate = getDelegate();
                View view = delegate.createView(parent, name, context, attrs);
                if (view != null && (view instanceof TextView)) {
                    ((TextView) view).setTypeface(mTypeface);
                }
                if(view !=null && (view instanceof EditText)){
                    ((EditText) view).setTypeface(mTypeface);
                }
                return view;
            }

            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                return null;
            }
        });
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        StatusBarUtil.setColor(this, ContextCompat.getColor(mContext, R.color.blue));
        ButterKnife.bind(this);
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
