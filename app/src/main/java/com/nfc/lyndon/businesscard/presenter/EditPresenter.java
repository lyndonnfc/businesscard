package com.nfc.lyndon.businesscard.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.app.Constants;
import com.nfc.lyndon.businesscard.base.BaseResponse;
import com.nfc.lyndon.businesscard.contract.EditContract;
import com.nfc.lyndon.businesscard.entity.UploadEntity;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.util.WeakHandler;

import java.io.File;

public class EditPresenter extends EditContract.EditPresenter {

    private static final int HANDLER_UPLOAD_LOGO_SUCCESS = 1;

    private static final int HANDLER_CREATE_SUCCESS = 2;

    private static final int HANDLER_FAILED = 3;

    private Context mContext;

    private WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_UPLOAD_LOGO_SUCCESS:
                    getView().showLogo((String) msg.obj);
                    break;
                case HANDLER_CREATE_SUCCESS:
                    ToastUtils.toastShort((String) msg.obj);
                    getView().finishActivity();
                    break;
                case HANDLER_FAILED:
                    ToastUtils.toastShort((String) msg.obj);
                    break;
            }
            return false;
        }
    });

    public EditPresenter(Context context) {
        this.mContext = context;
    }

    public void initView(ImageView view) {
        Glide.with(mContext)
                .load(R.drawable.img_avatar_bg)
                .apply(new RequestOptions().circleCrop())
                .into(view);
    }

    @Override
    public void uploadLogo(File file) {
        final EditContract.EditView mView = getView();
        mModel.uploadLogo(file, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                mView.hidLoading();
                BaseResponse<UploadEntity> baseResponse = JSON.parseObject(response.body(),
                        new TypeReference<BaseResponse<UploadEntity>>() {
                        });
                if (baseResponse != null && baseResponse.getStat() == Constants.SUCCESS) {
                    message = new Message();
                    message.what = HANDLER_UPLOAD_LOGO_SUCCESS;
                    message.obj = baseResponse.getResult().getImgUrl();
                    mHandler.sendMessage(message);
                } else {
                    if (baseResponse != null) {
                        message = new Message();
                        message.what = HANDLER_FAILED;
                        message.obj = baseResponse.getMsg();
                        mHandler.sendMessage(message);
                    }
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                mView.hidLoading();
                message = new Message();
                message.what = HANDLER_FAILED;
                message.obj = response.getException().getMessage();
                mHandler.sendMessage(message);
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                mView.showLoading("正在上传...");
            }
        });
    }

    @Override
    public void createCard(long uid, String logo, final String realName, String phone, String position,
                           String department, String companyName, String email, String address) {
        final EditContract.EditView mView = getView();
        mModel.createCard(uid, logo, realName, phone, position, department, companyName, email, address,
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        mView.hidLoading();
                        BaseResponse baseResponse = JSON.parseObject(response.body(), new TypeReference<BaseResponse>() {
                        });
                        if (baseResponse != null && baseResponse.getStat() == Constants.SUCCESS) {
                            message = new Message();
                            message.what = HANDLER_CREATE_SUCCESS;
                            message.obj = baseResponse.getMsg();
                            mHandler.sendMessage(message);
                        } else {
                            if (baseResponse != null) {
                                message = new Message();
                                message.what = HANDLER_FAILED;
                                message.obj = baseResponse.getMsg();
                                mHandler.sendMessage(message);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mView.hidLoading();
                        message = new Message();
                        message.what = HANDLER_FAILED;
                        message.obj = response.getException().getMessage();
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        mView.showLoading("正在创建...");
                    }

                });

    }

    @Override
    public void editCard(long uid, String logo, String realName, String phone, String position,
                         String department, String companyName, String email, String address) {
        final EditContract.EditView mView = getView();
        mModel.editCard(uid, logo, realName, phone, position, department, companyName, email, address,
                new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        mView.hidLoading();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mView.hidLoading();
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        mView.showLoading("正在修改...");
                    }
                });
    }


}
