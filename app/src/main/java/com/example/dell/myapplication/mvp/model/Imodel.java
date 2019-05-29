package com.example.dell.myapplication.mvp.model;

import com.example.dell.myapplication.mvp.persenter.ICallBask;

/**
 * Created by DELL on 2019/5/28.
 */

public interface Imodel {
    void getBannerDate(ICallBask iCallBask);

    void getArticleDate(int page, ICallBask iCallBask);
}
