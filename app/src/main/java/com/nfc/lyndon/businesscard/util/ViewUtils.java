package com.nfc.lyndon.businesscard.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;
import android.widget.RatingBar;

/**
 * Created by Administrator on 2018/3/9 0009.
 */
public class ViewUtils {

    /**
     * 动态设置Ratingbar高度，解决图片在不同分辨率手机拉伸问题
     * @param context
     * @param ratingBar
     * @param resourceId 本地图片资源Id
     */
    public static void setRatingBarHeight(Context context, RatingBar ratingBar, int resourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
        int height = bitmap.getHeight();
        ViewGroup.LayoutParams params = ratingBar.getLayoutParams();
        params.height = height;
        ratingBar.setLayoutParams(params);
    }
}
