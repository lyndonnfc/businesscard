package com.nfc.lyndon.businesscard.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.nfc.lyndon.businesscard.contract.DetailContract;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.ui.activity.TransferActivity;

public class DetailPresenter extends DetailContract.DetailPresenter{

    private Context mContext;

    public DetailPresenter(Context context){
        this.mContext = context;
    }

    public void toEdit(Bundle bundle){
        EditActivity.startActivity(mContext, bundle);
    }

    public void toTransfer(){
        Intent intent = new Intent(mContext, TransferActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void requestDetail(long id) {
        final DetailContract.DetailView mView = getView();

        mModel.requestDetail(id, new StringCallback() {
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
    public void deleteCard(long id) {
        final DetailContract.DetailView mView = getView();

        mModel.deleteCard(id, new StringCallback() {
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
