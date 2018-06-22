package com.nfc.lyndon.businesscard.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.nfc.lyndon.businesscard.BuildConfig;
import com.nfc.lyndon.businesscard.app.Constants;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/1/15 0015.
 */
public class AppUtils {

    /**
     * 获取手机IMEI号
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMEI(Context context) {
        String imei = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
            assert telephonyManager != null;
            imei = telephonyManager.getDeviceId();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return imei;
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    /**
     * 判断华为手机是否式刘海屏
     *
     * @param context
     * @return
     */
    public static boolean hasNotchInScreen(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("test", "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("test", "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
            Log.e("test", "hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }

    /**
     * 华为获取刘海尺寸
     *
     * @param context
     * @return
     */
    public static int[] getNotchSize(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("test", "getNotchSize ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("test", "getNotchSize NoSuchMethodException");
        } catch (Exception e) {
            Log.e("test", "getNotchSize Exception");
        } finally {
            return ret;
        }
    }

    /**
     * 打开相机
     *
     * @param context
     */
    public static void openCameraPage(Context context, Activity activity, String path) {
        //创建一个file，用来存储拍照后的照片
        File file = new File(path);
        try {
            if (file.exists()) {
                boolean delete = file.delete();//删除
            }
            boolean create = file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri imageuri;
        if (Build.VERSION.SDK_INT >= 24) {
            imageuri = FileProvider.getUriForFile(context,
                    "com.nfc.lyndon.businesscard.fileprovider", //可以是任意字符串
                    file);
        } else {
            imageuri = Uri.fromFile(file);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
        activity.startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE);
    }

    /**
     * 打开相机
     *
     * @param context
     */
    public static void openCameraPage(Context context, Fragment fragment, String path) {
        //创建一个file，用来存储拍照后的照片
        File file = new File(path);
        try {
            if (file.exists()) {
                boolean delete = file.delete();//删除
            }
            boolean create = file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri imageuri;
        if (Build.VERSION.SDK_INT >= 24) {
            imageuri = FileProvider.getUriForFile(context,
                    "com.nfc.lyndon.businesscard.fileprovider", //可以是任意字符串
                    file);
        } else {
            imageuri = Uri.fromFile(file);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
        fragment.startActivityForResult(intent, Constants.CAMERA_REQUEST_CODE);
    }

    /**
     * 打开相册
     */
    public static void openAlbumPage(Activity activity) {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(pickIntent, Constants.ALBUM_REQUEST_CODE);
    }

    /**
     * 打开相册
     */
    public static void openAlbumPage(Fragment fragment) {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        fragment.startActivityForResult(pickIntent, Constants.ALBUM_REQUEST_CODE);
    }

    /**
     * 调用系统照片的裁剪功能，修改编辑头像的选择模式(适配Android7.0)
     */
    public static Intent invokeSystemCrop(Uri uri, String path) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        intent.putExtra("scale", true);

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 9);
        intent.putExtra("aspectY", 5);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);

        File out = new File(path);
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        return intent;
    }
}
