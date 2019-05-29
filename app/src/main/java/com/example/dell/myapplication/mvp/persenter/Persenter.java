package com.example.dell.myapplication.mvp.persenter;

import com.example.dell.myapplication.beans.NewslistBean;
import com.example.dell.myapplication.beans.ResultsBean;
import com.example.dell.myapplication.mvp.model.Model;
import com.example.dell.myapplication.mvp.view.IView;

import java.util.List;

/**
 * Created by DELL on 2019/5/28.
 */

public class Persenter implements Ipersenter {
    private IView mIView;
    private final Model mModel;

    public Persenter(IView iView) {
        mIView = iView;
        mModel = new Model();
    }

    @Override
    public void setBannerDate() {
        if (mModel != null) {
            mModel.getBannerDate(new ICallBask() {
                @Override
                public void SuccessfulBanner(List<ResultsBean> bannerList) {
                    mIView.SuccessfulBanner(bannerList);
                }

                @Override
                public void successfulArticle(List<NewslistBean> articleList) {
                    mIView.successfulArticle(articleList);
                }

                @Override
                public void Error(String error) {
                    mIView.Error(error);
                }
            });
        }
    }

    @Override
    public void setArticleDate(int page) {
        mModel.getArticleDate(page, new ICallBask() {
            @Override
            public void SuccessfulBanner(List<ResultsBean> bannerList) {
                mIView.SuccessfulBanner(bannerList);
            }

            @Override
            public void successfulArticle(List<NewslistBean> articleList) {
                mIView.successfulArticle(articleList);
            }

            @Override
            public void Error(String error) {
                mIView.Error(error);
            }
        });
    }
}
