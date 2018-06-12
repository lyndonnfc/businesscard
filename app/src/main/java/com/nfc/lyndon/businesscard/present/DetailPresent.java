package com.nfc.lyndon.businesscard.present;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.nfc.lyndon.businesscard.base.BasePresent;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.ui.activity.TransferActivity;
import com.nfc.lyndon.businesscard.util.BitmapUtils;
import com.nfc.lyndon.businesscard.view.DetailView;

import permissions.dispatcher.NeedsPermission;

public class DetailPresent extends BasePresent<DetailView>{

    private Context mContext;

    public DetailPresent(Context context){
        this.mContext = context;
    }

    public void toEdit(){
        Intent intent = new Intent(mContext, EditActivity.class);
        mContext.startActivity(intent);
    }

    public void toTransfer(){
        Intent intent = new Intent(mContext, TransferActivity.class);
        mContext.startActivity(intent);
    }

}
