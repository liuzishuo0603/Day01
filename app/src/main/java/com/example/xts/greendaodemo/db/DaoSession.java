package com.example.xts.greendaodemo.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.dell.myapplication.beans.NewslistBean;

import com.example.xts.greendaodemo.db.NewslistBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig newslistBeanDaoConfig;

    private final NewslistBeanDao newslistBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        newslistBeanDaoConfig = daoConfigMap.get(NewslistBeanDao.class).clone();
        newslistBeanDaoConfig.initIdentityScope(type);

        newslistBeanDao = new NewslistBeanDao(newslistBeanDaoConfig, this);

        registerDao(NewslistBean.class, newslistBeanDao);
    }
    
    public void clear() {
        newslistBeanDaoConfig.clearIdentityScope();
    }

    public NewslistBeanDao getNewslistBeanDao() {
        return newslistBeanDao;
    }

}
