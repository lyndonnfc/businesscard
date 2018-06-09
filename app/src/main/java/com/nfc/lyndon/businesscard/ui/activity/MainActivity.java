package com.nfc.lyndon.businesscard.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.BaseActivity;
import com.nfc.lyndon.businesscard.base.BaseView;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.present.MainPresent;
import com.nfc.lyndon.businesscard.ui.fragment.CardListFragment;
import com.nfc.lyndon.businesscard.ui.fragment.SendCardFragment;
import com.nfc.lyndon.businesscard.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页面
 */
public class MainActivity extends MvpActivity<MainView, MainPresent> {

    @BindView(R.id.tv_list)
    TextView tvList;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.layout_content)
    FrameLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.initFragment();
    }

    @Override
    public MainPresent initPresenter() {
        return new MainPresent(this);
    }

    @OnClick({R.id.tv_list, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_list:
                presenter.replaceList();
                break;
            case R.id.tv_send:
                presenter.replaceSend();
                break;
        }
    }
}
