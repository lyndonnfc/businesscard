package com.nfc.lyndon.businesscard.ui.activity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.model.TransferModel;
import com.nfc.lyndon.businesscard.presenter.TransferPresenter;
import com.nfc.lyndon.businesscard.util.TextRecord;
import com.nfc.lyndon.businesscard.util.ToastUtils;

import java.nio.charset.Charset;
import java.util.Locale;

/**
 * 正在传输页面
 */
@SuppressLint("NewApi")
public class TransferActivity extends MvpActivity<TransferPresenter, TransferModel> implements
        NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback,
        NfcAdapter.ReaderCallback{

    private NfcAdapter mNfcAdapter;

    private PendingIntent mPendingIntent;

    private String content;// 要传送的名片内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = getIntent().getStringExtra("content");

        NfcManager manager = (NfcManager) getSystemService(Context.NFC_SERVICE);
        if (manager != null) {
            mNfcAdapter = manager.getDefaultAdapter();
        } else {
            ToastUtils.toastShort("此手机不支持NFC功能");
        }
        if (mNfcAdapter == null || !mNfcAdapter.isEnabled()) {
            ToastUtils.toastShort("请打开NFC");
        }

        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()), 0);

        mNfcAdapter.setNdefPushMessageCallback(this, this);
        mNfcAdapter.setOnNdefPushCompleteCallback(this, this);

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_transfering;
    }

    @Override
    protected TransferPresenter initPresenter() {
        return new TransferPresenter();
    }

    @Override
    protected TransferModel initModel() {
        return new TransferModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNfcAdapter != null)
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null,
                    null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNfcAdapter != null)
            mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        if (TextUtils.isEmpty(content))
            content = "传输名片失败";
        return new NdefMessage(
                new NdefRecord[]{createTextRecord(content)});
    }

    @Override
    public void onNdefPushComplete(NfcEvent nfcEvent) {
        ToastUtils.toastShort("传输完毕");
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //processIntent(intent);
    }

    /**
     * 根据文本创建 NdefRecord 这个对象
     */

    public NdefRecord createTextRecord(String text) {
        byte[] langBytes = Locale.CHINA.getLanguage().getBytes(
                Charset.forName("US-ASCII"));
        Charset utfEncoding = Charset.forName("UTF-8");
        byte[] textBytes = text.getBytes(utfEncoding);
        int utfBit = 0;
        char status = (char) (utfBit + langBytes.length);
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length,
                textBytes.length);
        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT, new byte[0], data);
    }

    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent
                .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        NdefMessage msg = (NdefMessage) rawMsgs[0];
        String text = TextRecord.parse(msg.getRecords()[0]).getText();
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        ToastUtils.toastShort("----------------------");
    }
}
