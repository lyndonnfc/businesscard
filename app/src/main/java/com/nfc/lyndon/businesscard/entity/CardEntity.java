package com.nfc.lyndon.businesscard.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class CardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @Property
    private long uid;

    @Property
    private String realName;

    @Property
    private String englishRealName;

    @Property
    private String phone;

    @Property
    private String mobile;

    @Property
    private String position;

    @Property
    private String englishPostion;

    @Property
    private String department;

    @Property
    private String englishDepartment;

    @Property
    private String companyName;

    @Property
    private String englishCompanyName;

    @Property
    private String email;

    @Property
    private String postcode;

    @Property
    private String webUrl;

    @Property
    private String fax;

    @Property
    private String qqAccount;

    @Property
    private String wechatAccount;

    @Property
    private String msnAccount;

    @Property
    private String weiboAccount;

    @Property
    private String companyAccount;

    @Property
    private String address;

    @Property
    private String englishAddress;

    @Property
    private String logo;

    @Property
    private String cardUrl;

    @Property
    private String otherMsg;

    @Generated(hash = 1746669704)
    public CardEntity(long id, long uid, String realName, String englishRealName,
            String phone, String mobile, String position, String englishPostion,
            String department, String englishDepartment, String companyName,
            String englishCompanyName, String email, String postcode, String webUrl,
            String fax, String qqAccount, String wechatAccount, String msnAccount,
            String weiboAccount, String companyAccount, String address,
            String englishAddress, String logo, String cardUrl, String otherMsg) {
        this.id = id;
        this.uid = uid;
        this.realName = realName;
        this.englishRealName = englishRealName;
        this.phone = phone;
        this.mobile = mobile;
        this.position = position;
        this.englishPostion = englishPostion;
        this.department = department;
        this.englishDepartment = englishDepartment;
        this.companyName = companyName;
        this.englishCompanyName = englishCompanyName;
        this.email = email;
        this.postcode = postcode;
        this.webUrl = webUrl;
        this.fax = fax;
        this.qqAccount = qqAccount;
        this.wechatAccount = wechatAccount;
        this.msnAccount = msnAccount;
        this.weiboAccount = weiboAccount;
        this.companyAccount = companyAccount;
        this.address = address;
        this.englishAddress = englishAddress;
        this.logo = logo;
        this.cardUrl = cardUrl;
        this.otherMsg = otherMsg;
    }

    @Generated(hash = 117867057)
    public CardEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEnglishRealName() {
        return englishRealName;
    }

    public void setEnglishRealName(String englishRealName) {
        this.englishRealName = englishRealName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEnglishPostion() {
        return englishPostion;
    }

    public void setEnglishPostion(String englishPostion) {
        this.englishPostion = englishPostion;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEnglishDepartment() {
        return englishDepartment;
    }

    public void setEnglishDepartment(String englishDepartment) {
        this.englishDepartment = englishDepartment;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEnglishCompanyName() {
        return englishCompanyName;
    }

    public void setEnglishCompanyName(String englishCompanyName) {
        this.englishCompanyName = englishCompanyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getQqAccount() {
        return qqAccount;
    }

    public void setQqAccount(String qqAccount) {
        this.qqAccount = qqAccount;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    public String getMsnAccount() {
        return msnAccount;
    }

    public void setMsnAccount(String msnAccount) {
        this.msnAccount = msnAccount;
    }

    public String getWeiboAccount() {
        return weiboAccount;
    }

    public void setWeiboAccount(String weiboAccount) {
        this.weiboAccount = weiboAccount;
    }

    public String getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(String companyAccount) {
        this.companyAccount = companyAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnglishAddress() {
        return englishAddress;
    }

    public void setEnglishAddress(String englishAddress) {
        this.englishAddress = englishAddress;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }
}
