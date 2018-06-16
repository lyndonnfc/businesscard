package com.nfc.lyndon.businesscard.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.nfc.lyndon.businesscard.R;

import java.util.Objects;

/**
 * 确定取消dialog
 */
public class ProgressDialog extends Dialog{

    private Context mContext;

    private TextView tvMessage;

    public ProgressDialog(@NonNull Context context) {
        super(context);
    }

    public ProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    protected ProgressDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);

        Objects.requireNonNull(getWindow()).setGravity(Gravity.CENTER);

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);

        ContentLoadingProgressBar progressBar = findViewById(R.id.progressBar);
        tvMessage = findViewById(R.id.tv_message);

        progressBar.getIndeterminateDrawable()
                .setColorFilter(ContextCompat.getColor(mContext, R.color.blue),
                        PorterDuff.Mode.MULTIPLY);

        setCanceledOnTouchOutside(false);
    }

    public void setMessage(String message){
        tvMessage.setText(message);
    }

}
