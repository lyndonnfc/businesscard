package com.nfc.lyndon.businesscard.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.contract.MainContract;
import com.nfc.lyndon.businesscard.ui.fragment.CardListFragment;
import com.nfc.lyndon.businesscard.ui.fragment.SendCardFragment;

public class MainPresenter extends MainContract.MainPresenter{

    private CardListFragment cardListFragment;
    private SendCardFragment sendCardFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public void initFragment(FragmentActivity context){
        cardListFragment = new CardListFragment();
        sendCardFragment = new SendCardFragment();
        fragmentManager = context.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_content, cardListFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void replaceList(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_content, cardListFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void replaceSend(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_content, sendCardFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
