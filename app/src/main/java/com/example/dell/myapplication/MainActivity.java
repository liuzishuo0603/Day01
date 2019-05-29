package com.example.dell.myapplication;
//刘子硕 2019/5/28/19:00

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.myapplication.fragments.HomeFragment;
import com.example.dell.myapplication.fragments.MeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mConnMain;
    private TabLayout mTabMain;
    private FragmentManager mSupportFragmentManager;
    private ArrayList<Fragment> mFragments;
    private int _position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSupportFragmentManager = getSupportFragmentManager();
        initView();
        initFragment();
        addFragment();
    }

    private void addFragment() {
        FragmentTransaction transaction = mSupportFragmentManager.beginTransaction();//开启事务,事务只能提交一次
        transaction.add(R.id.conn_main, mFragments.get(0));//添加fragment
        transaction.commit();//提交事务
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new MeFragment());
    }

    private void initView() {
        mConnMain = (FrameLayout) findViewById(R.id.conn_main);
        mTabMain = (TabLayout) findViewById(R.id.tab_main);
        for (int i = 0; i < 2; i++) {
            mTabMain.addTab(mTabMain.newTab().setCustomView(getTab(i)));
        }
        mTabMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switchFragment(position);
                _position = position;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void switchFragment(int position) {
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        Fragment fragment = mFragments.get(position);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.conn_main, fragment);
        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.hide(mFragments.get(_position));
        fragmentTransaction.commit();
    }

    private View getTab(int indxx) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView iv = view.findViewById(R.id.iv_tab);
        TextView tv = view.findViewById(R.id.tv_tab);
        if (indxx == 0) {
            iv.setImageResource(R.drawable.tab_home_item);
            tv.setText("首页");
        } else {
            iv.setImageResource(R.drawable.tab_me_item);
            tv.setText("我的");
        }
        return view;
    }
}
