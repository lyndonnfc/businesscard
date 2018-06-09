package com.nfc.lyndon.businesscard.present;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BasePresent;
import com.nfc.lyndon.businesscard.ui.fragment.CardListFragment;
import com.nfc.lyndon.businesscard.ui.fragment.SendCardFragment;
import com.nfc.lyndon.businesscard.view.MainView;

public class MainPresent extends BasePresent<MainView>{

    private CardListFragment cardListFragment;
    private SendCardFragment sendCardFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public MainPresent(FragmentActivity context){
        fragmentManager = context.getSupportFragmentManager();
    }

    public void initFragment(){
        cardListFragment = new CardListFragment();
        sendCardFragment = new SendCardFragment();
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
