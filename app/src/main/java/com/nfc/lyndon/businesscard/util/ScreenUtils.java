package com.nfc.lyndon.businesscard.util;

import android.content.Context;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class ScreenUtils {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
