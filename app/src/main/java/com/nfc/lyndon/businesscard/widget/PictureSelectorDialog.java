package com.nfc.lyndon.businesscard.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.nfc.lyndon.businesscard.R;

import java.util.Objects;

/**
 * 图片选择dialog
 */
public class PictureSelectorDialog extends Dialog implements View.OnClickListener{

    private OnDialogClickListener onDialogClickListener;

    public PictureSelectorDialog(@NonNull Context context) {
        super(context);
    }

    public PictureSelectorDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected PictureSelectorDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_picture_selector);

        Objects.requireNonNull(getWindow()).setGravity(Gravity.CENTER); //显示在底部
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);

        TextView tvCamera = findViewById(R.id.tv_camera);
        TextView tvGallery = findViewById(R.id.tv_gallery);

        tvCamera.setOnClickListener(this);
        tvGallery.setOnClickListener(this);

        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_camera:
                if (onDialogClickListener != null)
                    onDialogClickListener.camera();
                dismiss();
                break;
            case R.id.tv_gallery:
                if (onDialogClickListener != null)
                    onDialogClickListener.gallery();
                dismiss();
                break;
        }
    }


    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public interface OnDialogClickListener{
        void camera();
        void gallery();
    }

}
