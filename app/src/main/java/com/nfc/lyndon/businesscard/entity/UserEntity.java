package com.nfc.lyndon.businesscard.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserEntity {

    @Id
    private String userId;

    @Property (nameInDb = "username")
    private String username;

    @Generated(hash = 1849802058)
    public UserEntity(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
