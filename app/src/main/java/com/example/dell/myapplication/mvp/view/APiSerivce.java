package com.example.dell.myapplication.mvp.view;

import com.example.dell.myapplication.beans.ArticleRootBean;
import com.example.dell.myapplication.beans.BannerRootBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DELL on 2019/5/28.
 */

public interface APiSerivce {
    String mBannerUrl = "http://gank.io/";
    String mAricleUrl = "http://api.tianapi.com/";

    @GET("api/data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<BannerRootBean> getBannerDate();

    @GET("wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=20&page=")
    Observable<ArticleRootBean> getArticelDete(@Query("page") int page);
}
