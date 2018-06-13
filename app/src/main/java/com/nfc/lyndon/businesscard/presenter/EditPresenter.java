package com.nfc.lyndon.businesscard.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.contract.EditContract;

public class EditPresenter extends EditContract.EditPresenter {

    private Context mContext;

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
    public void createCard(long uid, String realName, String phone, String position,
                           String department, String companyName, String email, String address) {
        final EditContract.EditView mView = getView();
        mModel.createCard(uid, realName, phone, position, department, companyName, email, address,
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
                        mView.showLoading();
                    }

                });

    }

    @Override
    public void editCard(long uid, String realName, String phone, String position,
                         String department, String companyName, String email, String address) {
        final EditContract.EditView mView = getView();
        mModel.editCard(uid, realName, phone, position, department, companyName, email, address,
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
                        mView.showLoading();
                    }
                });
    }
}
