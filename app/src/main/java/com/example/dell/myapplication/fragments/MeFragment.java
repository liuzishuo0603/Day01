package com.example.dell.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.myapplication.CollectionActivity;
import com.example.dell.myapplication.R;

/**
 * Created by DELL on 2019/5/28.
 */

public class MeFragment extends Fragment {
    private View view;
    private NavigationView mNaFragmentMe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me_item, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mNaFragmentMe = (NavigationView) view.findViewById(R.id.na_fragment_me);
        mNaFragmentMe.setItemIconTintList(null);
        mNaFragmentMe.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.item_fragment_me_one:
                        startActivity(new Intent(getActivity(), CollectionActivity.class));
                        break;
                    case R.id.item_fragment_me_two:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_fragment_me_one_one:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_fragment_me_two_two:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_fragment_me_one_one_one:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_fragment_me_two_two_two:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_fragment_me_two_two_three:
                        Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
}
