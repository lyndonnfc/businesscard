package com.nfc.lyndon.businesscard.presenter;

import android.content.Context;
import android.content.Intent;

import com.nfc.lyndon.businesscard.contract.SendCardContract;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.ui.activity.TransferActivity;

public class SendCardPresent extends SendCardContract.SendCardPresenter {

    private Context mContext;

    public SendCardPresent(Context context){
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
