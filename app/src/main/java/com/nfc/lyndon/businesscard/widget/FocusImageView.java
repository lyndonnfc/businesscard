package com.nfc.lyndon.businesscard.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nfc.lyndon.businesscard.R;

/**
 * Created by Administrator on 2018/6/20.
 */

@SuppressLint("AppCompatCustomView")
public class FocusImageView extends ImageView {

    private Animation mAnimation;
    private Handler mHandler;
    private int mFocusImg;
    private int mFocusSucceedImg;
    private int mFocusFailedImg;

    public FocusImageView(Context context) {
        super(context);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.focusview_show);
        setVisibility(GONE);
        mHandler = new Handler();
    }

    public FocusImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.focusview_show);
        mHandler = new Handler();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FocusImageView);
        mFocusImg = a.getResourceId(R.styleable.FocusImageView_focus_focusing_id, 0);
        mFocusSucceedImg = a.getResourceId(R.styleable.FocusImageView_focus_success_id, 0);
        mFocusFailedImg = a.getResourceId(R.styleable.FocusImageView_focus_fail_id, 0);
        a.recycle();
        if((mFocusImg == 0) || (mFocusSucceedImg == 0) || (mFocusFailedImg == 0)) {
            throw new RuntimeException("Animation is null");
        }
    }

    public void startFocus(Point p) {
        if((mFocusImg == 0) || (mFocusSucceedImg == 0) || (mFocusFailedImg == 0)) {
            throw new RuntimeException("focus image is null");
        }
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)getLayoutParams();
        params.topMargin = (p.y - (getHeight() / 2));
        params.leftMargin = (p.x - (getWidth() / 2));
        setLayoutParams(params);
        setVisibility(VISIBLE);
        setImageResource(mFocusImg);
        startAnimation(mAnimation);
        mHandler.postDelayed(runnable, 600);
    }

    public void onFocusSuccess() {
        setImageResource(mFocusSucceedImg);
        mHandler.removeCallbacks(runnable, null);
        mHandler.postDelayed(runnable, 600);
    }

    public void onFocusFailed() {
        setImageResource(mFocusFailedImg);
        mHandler.removeCallbacks(runnable, null);
        mHandler.postDelayed(runnable, 600);
    }

    public void setFocusImg(int p1) {
        mFocusImg = p1;
    }

    public void setFocusSucceedImg(int p1) {
        mFocusSucceedImg = p1;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            setVisibility(GONE);
        }
    };

}
