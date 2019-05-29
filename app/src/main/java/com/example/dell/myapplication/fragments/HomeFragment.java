package com.example.dell.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.myapplication.DetailsActivity;
import com.example.dell.myapplication.MyApp;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.adapters.HomeFragmentAdapter;
import com.example.dell.myapplication.beans.NewslistBean;
import com.example.dell.myapplication.beans.ResultsBean;
import com.example.dell.myapplication.mvp.persenter.Persenter;
import com.example.dell.myapplication.mvp.view.IView;
import com.example.xts.greendaodemo.db.DaoSession;
import com.example.xts.greendaodemo.db.NewslistBeanDao;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by DELL on 2019/5/28.
 */

public class HomeFragment extends Fragment implements IView {
    private View view;
    private RecyclerView mRlvFragmentHome;
    private SmartRefreshLayout mSrlFragmentHome;
    private HomeFragmentAdapter mAdapter;
    private int mPage = 1;
    private int _position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_item, null);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        Persenter persenter = new Persenter(this);
        persenter.setArticleDate(mPage);
        persenter.setBannerDate();
    }

    private void initView(View view) {
        mRlvFragmentHome = (RecyclerView) view.findViewById(R.id.rlv_fragment_home);
        mSrlFragmentHome = (SmartRefreshLayout) view.findViewById(R.id.srl_fragment_home);
        mRlvFragmentHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlvFragmentHome.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new HomeFragmentAdapter(getActivity());
        mRlvFragmentHome.setAdapter(mAdapter);
        mSrlFragmentHome.setOnLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                initData();
                mSrlFragmentHome.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAdapter.mBannerList.clear();
                mPage = 1;
                mAdapter.mArtilcleList.clear();
                initData();
                mSrlFragmentHome.finishRefresh();
            }
        });
        mAdapter.setOnClickListener(new HomeFragmentAdapter.onClickListener() {
            @Override
            public void onClick(View v, int position) {
                NewslistBean newslistBean = mAdapter.mArtilcleList.get(position);
                String url = newslistBean.getUrl();
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        mAdapter.setOnLongClickListener(new HomeFragmentAdapter.onLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {
                _position = position;
            }
        });
        registerForContextMenu(mRlvFragmentHome);//注册上下文
    }

    @Override
    public void SuccessfulBanner(List<ResultsBean> bannerList) {
        mAdapter.mBannerList.addAll(bannerList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void successfulArticle(List<NewslistBean> articleList) {
        mAdapter.mArtilcleList.addAll(articleList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void Error(String error) {
        Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1, 1, 1, "收藏");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                DaoSession daoSession = MyApp.getmDaoSession();
                NewslistBean unique = daoSession.queryBuilder(NewslistBean.class).where(NewslistBeanDao.Properties.PicUrl.eq(mAdapter.mArtilcleList.get(_position).getPicUrl())).build().unique();
                if (unique == null) {
                    NewslistBean newslistBean = mAdapter.mArtilcleList.get(_position);
                    daoSession.insert(newslistBean);
                    Toast.makeText(getActivity(), "收藏成功!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "库里已有此文件!!!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }
}
