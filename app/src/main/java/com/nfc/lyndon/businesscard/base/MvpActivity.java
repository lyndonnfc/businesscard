package com.nfc.lyndon.businesscard.base;

import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.app.Constants;
import com.nfc.lyndon.businesscard.entity.CardDetailEntity;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.util.TextRecord;
import com.nfc.lyndon.businesscard.widget.ProgressDialog;

public abstract class MvpActivity<T extends BasePresenter, M extends BaseModel> extends BaseActivity {

    public T mPresenter;

    public M mModel;

    public ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent();
        intent.setAction("android.nfc.action.NDEF_DISCOVERED");
        intent.addCategory("android.intent.category.DEFAULT");

        //内部获取第一个类型参数的真实类型  ，反射new出对象
        mPresenter = initPresenter();
        //内部获取第二个类型参数的真实类型  ，反射new出对象
        mModel = initModel();
        //使得P层绑定M层和V层，持有M和V的引用
        mPresenter.attachModelView(mModel, this);
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
        hidDialog();
        dialog = null;
    }

    public abstract void initView();

    protected abstract T initPresenter();

    protected abstract M initModel();

    public void showDialog(String message) {
        dialog = new ProgressDialog(mContext, R.style.transparent_dialog);
        dialog.show();
        dialog.setMessage(message);
    }

    public void hidDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}