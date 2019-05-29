package com.example.dell.myapplication;

import android.app.Application;

import com.example.xts.greendaodemo.db.DaoMaster;
import com.example.xts.greendaodemo.db.DaoSession;

/**
 * Created by DELL on 2019/5/28.
 */

public class MyApp extends Application {

    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }

    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "user.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = daoMaster.newSession();
    }
}
