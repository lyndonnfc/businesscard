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
        fragmentManager = context.getSupportFragmentManager();
        replaceList();
    }

    public void replaceList(){
        fragmentTransaction = fragmentManager.beginTransaction();
        hindFragment(fragmentTransaction);
        if (cardListFragment == null){
            cardListFragment = new CardListFragment();
            fragmentTransaction.add(R.id.layout_content, cardListFragment);
        } else {
            fragmentTransaction.show(cardListFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void replaceSend(){
        fragmentTransaction = fragmentManager.beginTransaction();
        hindFragment(fragmentTransaction);
        if (sendCardFragment == null){
            sendCardFragment = new SendCardFragment();
            fragmentTransaction.add(R.id.layout_content, sendCardFragment);
        } else {
            fragmentTransaction.show(sendCardFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    //隐藏所有Fragment
    private void hindFragment(FragmentTransaction fragmentTransaction) {
        if (cardListFragment != null) {
            fragmentTransaction.hide(cardListFragment);
        }
        if (sendCardFragment != null) {
            fragmentTransaction.hide(sendCardFragment);
        }
    }

}
