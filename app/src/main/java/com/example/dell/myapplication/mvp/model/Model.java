package com.example.dell.myapplication.mvp.model;

import android.util.Log;

import com.example.dell.myapplication.beans.ArticleRootBean;
import com.example.dell.myapplication.beans.BannerRootBean;
import com.example.dell.myapplication.beans.NewslistBean;
import com.example.dell.myapplication.beans.ResultsBean;
import com.example.dell.myapplication.mvp.persenter.ICallBask;
import com.example.dell.myapplication.mvp.view.APiSerivce;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DELL on 2019/5/28.
 */

public class Model implements Imodel {
    private static final String TAG = "Model";

    @Override
    public void getBannerDate(final ICallBask iCallBask) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APiSerivce.mBannerUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(APiSerivce.class).getBannerDate().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerRootBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BannerRootBean bannerRootBean) {
                        List<ResultsBean> results = bannerRootBean.getResults();
                        if (results != null) {
                            iCallBask.SuccessfulBanner(results);
                        } else {
                            iCallBask.Error("banner加载失败!!!");
                        }
                        Log.e(TAG, "onNext: " + results.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getCause().getMessage());
                        iCallBask.Error(e.getCause().getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getArticleDate(int page, final ICallBask iCallBask) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APiSerivce.mAricleUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(APiSerivce.class).getArticelDete(page).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleRootBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(ArticleRootBean articleRootBean) {
                        List<NewslistBean> newslist = articleRootBean.getNewslist();
                        if (newslist != null) {
                            iCallBask.successfulArticle(newslist);
                        } else {
                            iCallBask.Error("文章列表加载失败");
                        }
                        Log.e(TAG, "onNext: " + newslist.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getCause().getMessage());
                        iCallBask.Error(e.getCause().getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
