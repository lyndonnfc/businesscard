package com.nfc.lyndon.businesscard.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.nfc.lyndon.businesscard.model.UserModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_MODEL".
*/
public class UserModelDao extends AbstractDao<UserModel, String> {

    public static final String TABLENAME = "USER_MODEL";

    /**
     * Properties of entity UserModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UserId = new Property(0, String.class, "userId", true, "USER_ID");
        public final static Property Username = new Property(1, String.class, "username", false, "USERNAME");
        public final static Property UsernameEn = new Property(2, String.class, "usernameEn", false, "USERNAMEEN");
        public final static Property Avatar = new Property(3, String.class, "avatar", false, "AVATAR");
        public final static Property Position = new Property(4, String.class, "position", false, "POSITION");
        public final static Property PositionEn = new Property(5, String.class, "positionEn", false, "POSITIONEN");
        public final static Property Company = new Property(6, String.class, "company", false, "COMPANY");
        public final static Property CompanyEn = new Property(7, String.class, "companyEn", false, "COMPANYEN");
        public final static Property Mobile = new Property(8, String.class, "mobile", false, "MOBILE");
        public final static Property Fax = new Property(9, String.class, "fax", false, "FAX");
        public final static Property Email = new Property(10, String.class, "email", false, "EMAIL");
        public final static Property Address = new Property(11, String.class, "address", false, "ADDRESS");
        public final static Property AddressEn = new Property(12, String.class, "addressEn", false, "ADDRESSEN");
    }


    public UserModelDao(DaoConfig config) {
        super(config);
    }
    
    public UserModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_MODEL\" (" + //
                "\"USER_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: userId
                "\"USERNAME\" TEXT," + // 1: username
                "\"USERNAMEEN\" TEXT," + // 2: usernameEn
                "\"AVATAR\" TEXT," + // 3: avatar
                "\"POSITION\" TEXT," + // 4: position
                "\"POSITIONEN\" TEXT," + // 5: positionEn
                "\"COMPANY\" TEXT," + // 6: company
                "\"COMPANYEN\" TEXT," + // 7: companyEn
                "\"MOBILE\" TEXT," + // 8: mobile
                "\"FAX\" TEXT," + // 9: fax
                "\"EMAIL\" TEXT," + // 10: email
                "\"ADDRESS\" TEXT," + // 11: address
                "\"ADDRESSEN\" TEXT);"); // 12: addressEn
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserModel entity) {
        stmt.clearBindings();
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(1, userId);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
 
        String usernameEn = entity.getUsernameEn();
        if (usernameEn != null) {
            stmt.bindString(3, usernameEn);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(4, avatar);
        }
 
        String position = entity.getPosition();
        if (position != null) {
            stmt.bindString(5, position);
        }
 
        String positionEn = entity.getPositionEn();
        if (positionEn != null) {
            stmt.bindString(6, positionEn);
        }
 
        String company = entity.getCompany();
        if (company != null) {
            stmt.bindString(7, company);
        }
 
        String companyEn = entity.getCompanyEn();
        if (companyEn != null) {
            stmt.bindString(8, companyEn);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(9, mobile);
        }
 
        String fax = entity.getFax();
        if (fax != null) {
            stmt.bindString(10, fax);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(11, email);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(12, address);
        }
 
        String addressEn = entity.getAddressEn();
        if (addressEn != null) {
            stmt.bindString(13, addressEn);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserModel entity) {
        stmt.clearBindings();
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(1, userId);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(2, username);
        }
 
        String usernameEn = entity.getUsernameEn();
        if (usernameEn != null) {
            stmt.bindString(3, usernameEn);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(4, avatar);
        }
 
        String position = entity.getPosition();
        if (position != null) {
            stmt.bindString(5, position);
        }
 
        String positionEn = entity.getPositionEn();
        if (positionEn != null) {
            stmt.bindString(6, positionEn);
        }
 
        String company = entity.getCompany();
        if (company != null) {
            stmt.bindString(7, company);
        }
 
        String companyEn = entity.getCompanyEn();
        if (companyEn != null) {
            stmt.bindString(8, companyEn);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(9, mobile);
        }
 
        String fax = entity.getFax();
        if (fax != null) {
            stmt.bindString(10, fax);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(11, email);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(12, address);
        }
 
        String addressEn = entity.getAddressEn();
        if (addressEn != null) {
            stmt.bindString(13, addressEn);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public UserModel readEntity(Cursor cursor, int offset) {
        UserModel entity = new UserModel( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // userId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // username
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // usernameEn
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // avatar
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // position
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // positionEn
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // company
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // companyEn
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // mobile
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // fax
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // email
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // address
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // addressEn
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserModel entity, int offset) {
        entity.setUserId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUsername(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUsernameEn(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAvatar(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPosition(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPositionEn(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCompany(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCompanyEn(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMobile(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFax(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setEmail(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAddress(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setAddressEn(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final String updateKeyAfterInsert(UserModel entity, long rowId) {
        return entity.getUserId();
    }
    
    @Override
    public String getKey(UserModel entity) {
        if(entity != null) {
            return entity.getUserId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserModel entity) {
        return entity.getUserId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
