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
 * 确定取消dialog
 */
public class ConfirmOrCancelDialog extends Dialog implements View.OnClickListener{

    private TextView tvMessage;

    private OnDialogClickListener onDialogClickListener;

    public ConfirmOrCancelDialog(@NonNull Context context) {
        super(context);
    }

    public ConfirmOrCancelDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ConfirmOrCancelDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm_cancel);

        Objects.requireNonNull(getWindow()).setGravity(Gravity.CENTER); //显示在底部

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);

        tvMessage = findViewById(R.id.tv_message);
        TextView tvConfirm = findViewById(R.id.tv_confirm);
        TextView tvCancel = findViewById(R.id.tv_cancel);

        tvConfirm.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_confirm:
                if (onDialogClickListener != null)
                    onDialogClickListener.confirm();
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public void setMessage(String message){
        tvMessage.setText(message);
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public interface OnDialogClickListener{
        void confirm();
    }

}
