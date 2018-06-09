package com.nfc.lyndon.businesscard.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lzy.okgo.OkGo;

public abstract class MvpActivity<V,P extends BasePresent<V>> extends BaseActivity{

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        OkGo.getInstance().cancelTag(this);
        super.onDestroy();
    }

    public abstract P initPresenter();

}