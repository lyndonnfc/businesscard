package com.nfc.lyndon.businesscard.entity;

public class UserEntity {

    private int uid;

    private boolean isValid; // 验证码是否有效：false-无

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
