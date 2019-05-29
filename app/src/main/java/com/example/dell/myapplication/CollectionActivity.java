package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dell.myapplication.adapters.CollectionAdapter;
import com.example.dell.myapplication.beans.NewslistBean;
import com.example.xts.greendaodemo.db.DaoSession;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    private RecyclerView mRlvCollection;
    private SmartRefreshLayout mSrlCollection;
    private CollectionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initView();
        initData();
    }

    private static final String TAG = "CollectionActivity";

    private void initData() {
        DaoSession daoSession = MyApp.getmDaoSession();
        List<NewslistBean> list = daoSession.loadAll(NewslistBean.class);
        Log.e(TAG, "initData: " + list.size());
        if (list.size() > 0) {
            mAdapter.mList.addAll(list);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        mRlvCollection = (RecyclerView) findViewById(R.id.rlv_collection);
        mSrlCollection = (SmartRefreshLayout) findViewById(R.id.srl_collection);
        mRlvCollection.setLayoutManager(new LinearLayoutManager(this));
        mRlvCollection.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new CollectionAdapter(this);
        mRlvCollection.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "设置").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "你点击了" + item.getTitle() + "~~~", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
