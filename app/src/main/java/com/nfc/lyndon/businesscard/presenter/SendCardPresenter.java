package com.nfc.lyndon.businesscard.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nfc.lyndon.businesscard.contract.SendCardContract;
import com.nfc.lyndon.businesscard.ui.activity.EditActivity;
import com.nfc.lyndon.businesscard.ui.activity.TransferActivity;

public class SendCardPresenter extends SendCardContract.SendCardPresenter {

    private Context mContext;

    public SendCardPresenter(Context context){
        this.mContext = context;
    }

    /**
     * 编辑名片
     * @param bundle bundle
     */
    public void toEdit(Bundle bundle){
        EditActivity.startActivity(mContext, bundle);
    }

    /**
     * nfc递名片
     */
    public void toTransfer(){
        Intent intent = new Intent(mContext, TransferActivity.class);
        mContext.startActivity(intent);
    }
}
