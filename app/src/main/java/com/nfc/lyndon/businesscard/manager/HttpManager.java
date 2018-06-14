package com.nfc.lyndon.businesscard.manager;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.nfc.lyndon.businesscard.app.ApiService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求管理
 */
public class HttpManager {

    private static volatile HttpManager mInstance = null;

    private HttpManager() {

    }

    public static synchronized HttpManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new HttpManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取验证码
     *
     * @param mobile   手机号
     * @param callback callback
     */
    public void getCode(String mobile, StringCallback callback) {
        OkGo.<String>get(ApiService.GET_CODE_URL)
                .tag(this)
                .params("mobile", mobile)
                .execute(callback);
    }

    /**
     * 登录
     *
     * @param vefifyCode 验证码
     * @param mobile     手机
     * @param callback   callback
     */
    public void login(String vefifyCode, String mobile, StringCallback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("vefifyCode", vefifyCode);
        params.put("mobile", mobile);
        OkGo.<String>post(ApiService.LOGIN_URL)
                .tag(this)
                .upJson(JSON.toJSONString(params))
                .execute(callback);
    }

    /**
     * 获取名片列表
     *
     * @param uid      用户id
     * @param keyword  关键字
     * @param callback callback
     */
    public void getCardList(long uid, String keyword, StringCallback callback) {
        OkGo.<String>get(ApiService.GET_CARD_LIST)
                .tag(this)
                .params("uid", uid)
                .params("keyword", keyword)
                .execute(callback);
    }

    /**
     * 获取名片详情
     *
     * @param cardId   名片id
     * @param callback callback
     */
    public void getCardDetail(long cardId, StringCallback callback) {
        OkGo.<String>post(ApiService.GET_CARD_DETAIL)
                .tag(this)
                .params("cardId", cardId)
                .execute(callback);
    }

    /**
     * 创建名片
     *
     * @param uid         用户id
     * @param logo        logo
     * @param realName    真实姓名
     * @param phone       手机号
     * @param position    职位
     * @param department  部门
     * @param companyName 公司名称
     * @param email       邮箱
     * @param address     地址
     * @param callback    callback
     */
    public void createCard(long uid, String logo, String realName, String phone, String position,
                           String department, String companyName, String email, String address,
                           StringCallback callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        params.put("logo", logo);
        params.put("realName", realName);
        params.put("phone", phone);
        params.put("position", position);
        params.put("department", department);
        params.put("companyName", companyName);
        params.put("email", email);
        params.put("address", address);
        OkGo.<String>post(ApiService.CREATE_BUSINESS_CARD)
                .tag(this)
                .upJson(JSON.toJSONString(params))
                .execute(callback);
    }

    /**
     * 修改名片
     *
     * @param uid         用户id
     * @param logo        logo
     * @param realName    真实姓名
     * @param phone       手机号
     * @param position    职位
     * @param department  部门
     * @param companyName 公司名称
     * @param email       邮箱
     * @param address     地址
     * @param callback    callback
     */
    public void updateCard(long uid, String logo, String realName, String phone, String position,
                           String department, String companyName, String email, String address,
                           StringCallback callback) {
        OkGo.<String>post(ApiService.UPDATE_BUSINESS_CARD)
                .tag(this)
                .params("uid", uid)
                .params("logo", logo)
                .params("realName", realName)
                .params("phone", phone)
                .params("position", position)
                .params("department", department)
                .params("companyName", companyName)
                .params("email", email)
                .params("address", address)
                .execute(callback);
    }

    /**
     * 上传识别名片
     *
     * @param file     名片file
     * @param callback callback
     */
    public void uploadCardFile(File file, StringCallback callback) {
        OkGo.<String>post(ApiService.UPLOAD_BUSINESS_CARD)
                .tag(this)
                .params("image", file)
                .execute(callback);
    }

    /**
     * 上传头像
     *
     * @param file     名片file
     * @param callback callback
     */
    public void uploadAvatar(File file, StringCallback callback) {
        OkGo.<String>post(ApiService.UPLOAD_AVATAR)
                .tag(this)
                .params("image", file)
                .execute(callback);
    }

    /**
     * 删除名片
     *
     * @param cardId   名片id
     * @param callback callback
     */
    public void deleteCard(long cardId, StringCallback callback) {
        OkGo.<String>post(ApiService.DELETE_BUSINESS_CARD)
                .tag(this)
                .params("cardId", cardId)
                .execute(callback);
    }
}
