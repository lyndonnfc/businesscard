package com.nfc.lyndon.businesscard.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.model.MainModel;
import com.nfc.lyndon.businesscard.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主页面
 */
public class MainActivity extends MvpActivity<MainPresenter, MainModel>{

    @BindView(R.id.tv_list)
    TextView tvList;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.layout_content)
    FrameLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        mPresenter.initFragment(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @OnClick({R.id.tv_list, R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_list:
                mPresenter.replaceList();
                break;
            case R.id.tv_send:
                mPresenter.replaceSend();
                break;
        }
    }
}
