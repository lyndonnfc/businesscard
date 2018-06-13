package com.nfc.lyndon.businesscard.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.nfc.lyndon.businesscard.entity.CardEntity;

import com.nfc.lyndon.businesscard.gen.CardEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cardEntityDaoConfig;

    private final CardEntityDao cardEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cardEntityDaoConfig = daoConfigMap.get(CardEntityDao.class).clone();
        cardEntityDaoConfig.initIdentityScope(type);

        cardEntityDao = new CardEntityDao(cardEntityDaoConfig, this);

        registerDao(CardEntity.class, cardEntityDao);
    }
    
    public void clear() {
        cardEntityDaoConfig.clearIdentityScope();
    }

    public CardEntityDao getCardEntityDao() {
        return cardEntityDao;
    }

}
