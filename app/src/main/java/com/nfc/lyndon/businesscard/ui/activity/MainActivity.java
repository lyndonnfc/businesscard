package com.nfc.lyndon.businesscard.ui.activity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.entity.CardEntity;
import com.nfc.lyndon.businesscard.model.MainModel;
import com.nfc.lyndon.businesscard.presenter.MainPresenter;
import com.nfc.lyndon.businesscard.util.TextRecord;

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Parcelable[] rawMsgs = intent
                .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (rawMsgs != null) {
            NdefMessage msg = (NdefMessage) rawMsgs[0];
            if (msg != null && msg.getRecords().length > 0) {
                TextRecord textRecord = TextRecord.parse(msg.getRecords()[0]);
                if (textRecord != null) {
                    String text = textRecord.getText();
                    CardEntity cardEntity = JSON.parseObject(text, CardEntity.class);
                    Intent i = new Intent(mContext, EditActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isCreate", true);
                    bundle.putSerializable("cardInfo", cardEntity);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        }
        intent.removeExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
    }
}
