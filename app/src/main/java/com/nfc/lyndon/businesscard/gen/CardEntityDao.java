package com.nfc.lyndon.businesscard.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nfc.lyndon.businesscard.entity.CardEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CARD_ENTITY".
*/
public class CardEntityDao extends AbstractDao<CardEntity, Long> {

    public static final String TABLENAME = "CARD_ENTITY";

    /**
     * Properties of entity CardEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Uid = new Property(1, long.class, "uid", false, "UID");
        public final static Property RealName = new Property(2, String.class, "realName", false, "REAL_NAME");
        public final static Property EnglishRealName = new Property(3, String.class, "englishRealName", false, "ENGLISH_REAL_NAME");
        public final static Property Phone = new Property(4, String.class, "phone", false, "PHONE");
        public final static Property Mobile = new Property(5, String.class, "mobile", false, "MOBILE");
        public final static Property Position = new Property(6, String.class, "position", false, "POSITION");
        public final static Property EnglishPostion = new Property(7, String.class, "englishPostion", false, "ENGLISH_POSTION");
        public final static Property Department = new Property(8, String.class, "department", false, "DEPARTMENT");
        public final static Property EnglishDepartment = new Property(9, String.class, "englishDepartment", false, "ENGLISH_DEPARTMENT");
        public final static Property CompanyName = new Property(10, String.class, "companyName", false, "COMPANY_NAME");
        public final static Property EnglishCompanyName = new Property(11, String.class, "englishCompanyName", false, "ENGLISH_COMPANY_NAME");
        public final static Property Email = new Property(12, String.class, "email", false, "EMAIL");
        public final static Property Postcode = new Property(13, String.class, "postcode", false, "POSTCODE");
        public final static Property WebUrl = new Property(14, String.class, "webUrl", false, "WEB_URL");
        public final static Property Fax = new Property(15, String.class, "fax", false, "FAX");
        public final static Property QqAccount = new Property(16, String.class, "qqAccount", false, "QQ_ACCOUNT");
        public final static Property WechatAccount = new Property(17, String.class, "wechatAccount", false, "WECHAT_ACCOUNT");
        public final static Property MsnAccount = new Property(18, String.class, "msnAccount", false, "MSN_ACCOUNT");
        public final static Property WeiboAccount = new Property(19, String.class, "weiboAccount", false, "WEIBO_ACCOUNT");
        public final static Property CompanyAccount = new Property(20, String.class, "companyAccount", false, "COMPANY_ACCOUNT");
        public final static Property Address = new Property(21, String.class, "address", false, "ADDRESS");
        public final static Property EnglishAddress = new Property(22, String.class, "englishAddress", false, "ENGLISH_ADDRESS");
        public final static Property Logo = new Property(23, String.class, "logo", false, "LOGO");
        public final static Property CardUrl = new Property(24, String.class, "cardUrl", false, "CARD_URL");
        public final static Property OtherMsg = new Property(25, String.class, "otherMsg", false, "OTHER_MSG");
    }


    public CardEntityDao(DaoConfig config) {
        super(config);
    }
    
    public CardEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CARD_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"UID\" INTEGER NOT NULL ," + // 1: uid
                "\"REAL_NAME\" TEXT," + // 2: realName
                "\"ENGLISH_REAL_NAME\" TEXT," + // 3: englishRealName
                "\"PHONE\" TEXT," + // 4: phone
                "\"MOBILE\" TEXT," + // 5: mobile
                "\"POSITION\" TEXT," + // 6: position
                "\"ENGLISH_POSTION\" TEXT," + // 7: englishPostion
                "\"DEPARTMENT\" TEXT," + // 8: department
                "\"ENGLISH_DEPARTMENT\" TEXT," + // 9: englishDepartment
                "\"COMPANY_NAME\" TEXT," + // 10: companyName
                "\"ENGLISH_COMPANY_NAME\" TEXT," + // 11: englishCompanyName
                "\"EMAIL\" TEXT," + // 12: email
                "\"POSTCODE\" TEXT," + // 13: postcode
                "\"WEB_URL\" TEXT," + // 14: webUrl
                "\"FAX\" TEXT," + // 15: fax
                "\"QQ_ACCOUNT\" TEXT," + // 16: qqAccount
                "\"WECHAT_ACCOUNT\" TEXT," + // 17: wechatAccount
                "\"MSN_ACCOUNT\" TEXT," + // 18: msnAccount
                "\"WEIBO_ACCOUNT\" TEXT," + // 19: weiboAccount
                "\"COMPANY_ACCOUNT\" TEXT," + // 20: companyAccount
                "\"ADDRESS\" TEXT," + // 21: address
                "\"ENGLISH_ADDRESS\" TEXT," + // 22: englishAddress
                "\"LOGO\" TEXT," + // 23: logo
                "\"CARD_URL\" TEXT," + // 24: cardUrl
                "\"OTHER_MSG\" TEXT);"); // 25: otherMsg
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CARD_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CardEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getUid());
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(3, realName);
        }
 
        String englishRealName = entity.getEnglishRealName();
        if (englishRealName != null) {
            stmt.bindString(4, englishRealName);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(6, mobile);
        }
 
        String position = entity.getPosition();
        if (position != null) {
            stmt.bindString(7, position);
        }
 
        String englishPostion = entity.getEnglishPostion();
        if (englishPostion != null) {
            stmt.bindString(8, englishPostion);
        }
 
        String department = entity.getDepartment();
        if (department != null) {
            stmt.bindString(9, department);
        }
 
        String englishDepartment = entity.getEnglishDepartment();
        if (englishDepartment != null) {
            stmt.bindString(10, englishDepartment);
        }
 
        String companyName = entity.getCompanyName();
        if (companyName != null) {
            stmt.bindString(11, companyName);
        }
 
        String englishCompanyName = entity.getEnglishCompanyName();
        if (englishCompanyName != null) {
            stmt.bindString(12, englishCompanyName);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(13, email);
        }
 
        String postcode = entity.getPostcode();
        if (postcode != null) {
            stmt.bindString(14, postcode);
        }
 
        String webUrl = entity.getWebUrl();
        if (webUrl != null) {
            stmt.bindString(15, webUrl);
        }
 
        String fax = entity.getFax();
        if (fax != null) {
            stmt.bindString(16, fax);
        }
 
        String qqAccount = entity.getQqAccount();
        if (qqAccount != null) {
            stmt.bindString(17, qqAccount);
        }
 
        String wechatAccount = entity.getWechatAccount();
        if (wechatAccount != null) {
            stmt.bindString(18, wechatAccount);
        }
 
        String msnAccount = entity.getMsnAccount();
        if (msnAccount != null) {
            stmt.bindString(19, msnAccount);
        }
 
        String weiboAccount = entity.getWeiboAccount();
        if (weiboAccount != null) {
            stmt.bindString(20, weiboAccount);
        }
 
        String companyAccount = entity.getCompanyAccount();
        if (companyAccount != null) {
            stmt.bindString(21, companyAccount);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(22, address);
        }
 
        String englishAddress = entity.getEnglishAddress();
        if (englishAddress != null) {
            stmt.bindString(23, englishAddress);
        }
 
        String logo = entity.getLogo();
        if (logo != null) {
            stmt.bindString(24, logo);
        }
 
        String cardUrl = entity.getCardUrl();
        if (cardUrl != null) {
            stmt.bindString(25, cardUrl);
        }
 
        String otherMsg = entity.getOtherMsg();
        if (otherMsg != null) {
            stmt.bindString(26, otherMsg);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CardEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getUid());
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(3, realName);
        }
 
        String englishRealName = entity.getEnglishRealName();
        if (englishRealName != null) {
            stmt.bindString(4, englishRealName);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(6, mobile);
        }
 
        String position = entity.getPosition();
        if (position != null) {
            stmt.bindString(7, position);
        }
 
        String englishPostion = entity.getEnglishPostion();
        if (englishPostion != null) {
            stmt.bindString(8, englishPostion);
        }
 
        String department = entity.getDepartment();
        if (department != null) {
            stmt.bindString(9, department);
        }
 
        String englishDepartment = entity.getEnglishDepartment();
        if (englishDepartment != null) {
            stmt.bindString(10, englishDepartment);
        }
 
        String companyName = entity.getCompanyName();
        if (companyName != null) {
            stmt.bindString(11, companyName);
        }
 
        String englishCompanyName = entity.getEnglishCompanyName();
        if (englishCompanyName != null) {
            stmt.bindString(12, englishCompanyName);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(13, email);
        }
 
        String postcode = entity.getPostcode();
        if (postcode != null) {
            stmt.bindString(14, postcode);
        }
 
        String webUrl = entity.getWebUrl();
        if (webUrl != null) {
            stmt.bindString(15, webUrl);
        }
 
        String fax = entity.getFax();
        if (fax != null) {
            stmt.bindString(16, fax);
        }
 
        String qqAccount = entity.getQqAccount();
        if (qqAccount != null) {
            stmt.bindString(17, qqAccount);
        }
 
        String wechatAccount = entity.getWechatAccount();
        if (wechatAccount != null) {
            stmt.bindString(18, wechatAccount);
        }
 
        String msnAccount = entity.getMsnAccount();
        if (msnAccount != null) {
            stmt.bindString(19, msnAccount);
        }
 
        String weiboAccount = entity.getWeiboAccount();
        if (weiboAccount != null) {
            stmt.bindString(20, weiboAccount);
        }
 
        String companyAccount = entity.getCompanyAccount();
        if (companyAccount != null) {
            stmt.bindString(21, companyAccount);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(22, address);
        }
 
        String englishAddress = entity.getEnglishAddress();
        if (englishAddress != null) {
            stmt.bindString(23, englishAddress);
        }
 
        String logo = entity.getLogo();
        if (logo != null) {
            stmt.bindString(24, logo);
        }
 
        String cardUrl = entity.getCardUrl();
        if (cardUrl != null) {
            stmt.bindString(25, cardUrl);
        }
 
        String otherMsg = entity.getOtherMsg();
        if (otherMsg != null) {
            stmt.bindString(26, otherMsg);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public CardEntity readEntity(Cursor cursor, int offset) {
        CardEntity entity = new CardEntity( //
            cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // uid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // realName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // englishRealName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phone
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // mobile
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // position
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // englishPostion
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // department
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // englishDepartment
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // companyName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // englishCompanyName
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // email
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // postcode
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // webUrl
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // fax
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // qqAccount
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // wechatAccount
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // msnAccount
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // weiboAccount
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // companyAccount
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // address
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // englishAddress
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // logo
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // cardUrl
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25) // otherMsg
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CardEntity entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setUid(cursor.getLong(offset + 1));
        entity.setRealName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEnglishRealName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMobile(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPosition(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setEnglishPostion(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setDepartment(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setEnglishDepartment(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCompanyName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setEnglishCompanyName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setEmail(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setPostcode(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setWebUrl(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setFax(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setQqAccount(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setWechatAccount(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setMsnAccount(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setWeiboAccount(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setCompanyAccount(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setAddress(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setEnglishAddress(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setLogo(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setCardUrl(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setOtherMsg(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CardEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CardEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CardEntity entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
