package com.nfc.lyndon.businesscard.util;

import android.content.Context;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/2/27 0027.
 */

public class StringUtils{

    /**
     * 移动号段：139 138 137 136 135 134 147 150 151 152 157 158 159 178 182 183 184 187 188 198
     * 联通号段：130 131 132 155 156 166 185 186 145 176
     * 电信号段：133 153 177 173 180 181 189 199
     * 虚拟运营商号段：170 171
     *
     * @return
     */
    public static boolean isMobileNo(String phone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0-9])|(19[89]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static boolean isNull(String str) {
        if (str == null || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * md5加密
     *
     * @param key
     * @return
     */
    public static String getMd5(String key) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = key.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 设置资源文本
     * @param context
     * @param res
     * @param str
     * @return
     */
    public static String getReString(Context context, int res, Object... str){
        String temp = context.getResources().getString(res);
        return String.format(temp, str);
    }

    /**
     * 去除小數點無效的0
     * @param d
     * @return
     */
    public static String deleteZero(double d){
        String s = String.valueOf(d);
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉后面无用的零
            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return  s;
    }

    private static SimpleDateFormat sdf = null;

    /**
     * 毫秒转字符串日期格式
     * @param l
     * @param strPattern
     * @return
     */
    public static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable e) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        return sdf == null ? "NULL" : sdf.format(l);
    }
}
