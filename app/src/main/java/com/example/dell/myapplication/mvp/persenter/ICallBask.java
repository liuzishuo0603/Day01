package com.example.dell.myapplication.mvp.persenter;

import com.example.dell.myapplication.beans.NewslistBean;
import com.example.dell.myapplication.beans.ResultsBean;

import java.util.List;

/**
 * Created by DELL on 2019/5/28.
 */

public interface ICallBask {
    void SuccessfulBanner(List<ResultsBean> bannerList);

    void successfulArticle(List<NewslistBean> articleList);

    void Error(String error);
}
