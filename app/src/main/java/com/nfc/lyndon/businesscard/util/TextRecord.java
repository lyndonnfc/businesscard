package com.nfc.lyndon.businesscard.util;

import android.nfc.NdefRecord;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by Administrator on 2018/6/17.
 */
public class TextRecord {

    private final String mText;

    private TextRecord(String text) {
        mText = text;
    }

    public String getText() {
        return mText;
    }

    // 将纯文本内容从NdefRecord对象中解析出来
    public static TextRecord parse(NdefRecord record) {
        if (record.getTnf() != NdefRecord.TNF_WELL_KNOWN)
            return null;
        if (!Arrays.equals(record.getType(), NdefRecord.RTD_TEXT))
            return null;

        try {
            byte[] payload = record.getPayload();
            String textEncoding = ((payload[0] & 0x80) == 0) ? "UTF-8" : "UTF-16";
            int languageCodeLength = payload[0] & 0x3f;
            String languageCode = new String(payload, 1, languageCodeLength,
                    "US-ASCII");
            String text = new String(payload, languageCodeLength + 1,
                    payload.length - languageCodeLength - 1, textEncoding);
            return new TextRecord(text);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
