package com.nfc.lyndon.businesscard.util;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.TextViewCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nfc.lyndon.businesscard.app.BCApplication;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class ToastUtils{

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static WeakReference<Toast> sWeakToast;

    public static void toastShort(String msg){
        show(msg, Toast.LENGTH_SHORT);
    }

    public static void toastLong(String msg){
        show(msg, Toast.LENGTH_LONG);
    }

    public static void toastTime(String msg, int time){
        show(msg, time);
    }

    private static void show(final CharSequence text, final int duration) {
        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                cancel();
                final Toast toast = Toast.makeText(BCApplication.getInstance(), text, duration);
                sWeakToast = new WeakReference<>(toast);
                final TextView tvMessage = toast.getView().findViewById(android.R.id.message);
                TextViewCompat.setTextAppearance(tvMessage, android.R.style.TextAppearance);
                tvMessage.setTextColor(Color.WHITE);
                tvMessage.setTextSize(16);
                tvMessage.setPadding(ScreenUtils.dip2px(BCApplication.getInstance(), 16),
                        ScreenUtils.dip2px(BCApplication.getInstance(), 8),
                        ScreenUtils.dip2px(BCApplication.getInstance(), 16),
                        ScreenUtils.dip2px(BCApplication.getInstance(), 8));
                toast.setGravity(Gravity.CENTER,0,0);
                setBg(toast, tvMessage);
                toast.show();
            }
        });
    }

    private static void setBg(final Toast toast, final TextView tvMsg) {
        View toastView = toast.getView();
        toastView.setBackgroundColor(Color.parseColor("#1CABEA"));
        tvMsg.setBackgroundColor(Color.TRANSPARENT);
    }

    /**
     * Cancel the toast.
     */
    public static void cancel() {
        final Toast toast;
        if (sWeakToast != null && (toast = sWeakToast.get()) != null) {
            toast.cancel();
            sWeakToast = null;
        }
    }
}
