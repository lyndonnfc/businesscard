package com.nfc.lyndon.businesscard.model;

import com.nfc.lyndon.businesscard.contract.CardListContract;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserModel implements CardListContract.CardListModel{

    @Id
    private String userId;

    @Property (nameInDb = "USERNAME")
    private String username;

    @Property (nameInDb = "USERNAMEEN")
    private String usernameEn;

    @Property (nameInDb = "AVATAR")
    private String avatar;

    @Property (nameInDb = "POSITION")
    private String position;

    @Property (nameInDb = "POSITIONEN")
    private String positionEn;

    @Property (nameInDb = "COMPANY")
    private String company;

    @Property (nameInDb = "COMPANYEN")
    private String companyEn;

    @Property (nameInDb = "MOBILE")
    private String mobile;

    @Property (nameInDb = "FAX")
    private String fax;

    @Property (nameInDb = "EMAIL")
    private String email;

    @Property (nameInDb = "ADDRESS")
    private String address;

    @Property (nameInDb = "ADDRESSEN")
    private String addressEn;

    @Generated(hash = 1730937666)
    public UserModel(String userId, String username, String usernameEn,
            String avatar, String position, String positionEn, String company,
            String companyEn, String mobile, String fax, String email,
            String address, String addressEn) {
        this.userId = userId;
        this.username = username;
        this.usernameEn = usernameEn;
        this.avatar = avatar;
        this.position = position;
        this.positionEn = positionEn;
        this.company = company;
        this.companyEn = companyEn;
        this.mobile = mobile;
        this.fax = fax;
        this.email = email;
        this.address = address;
        this.addressEn = addressEn;
    }

    @Generated(hash = 782181818)
    public UserModel() {
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

    public String getUsernameEn() {
        return this.usernameEn;
    }

    public void setUsernameEn(String usernameEn) {
        this.usernameEn = usernameEn;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionEn() {
        return this.positionEn;
    }

    public void setPositionEn(String positionEn) {
        this.positionEn = positionEn;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyEn() {
        return this.companyEn;
    }

    public void setCompanyEn(String companyEn) {
        this.companyEn = companyEn;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressEn() {
        return this.addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    @Override
    public List<UserModel> getData() {
        List<UserModel> mData = new ArrayList<>();
        UserModel entity;
        for (int i = 0; i < 10; i++) {
            entity = new UserModel(i+"", "范冰冰","Fan Bingbing",
                    "http://img.zcool.cn/community/01432f5958ecc1a8012193a375857c.jpg",
                    "设计总监", "Design Director", "深圳市腾讯计算机系统有限公司",
                    "Shenzhen city Tencent computer system Co.Ltd.", "18674036966",
                    "123456789", "888888@qq.com", "广东省深圳市", "Guangdong province Shenzhen city");
            mData.add(entity);
        }
        return mData;
    }
}
