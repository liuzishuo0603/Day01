package com.example.dell.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.beans.NewslistBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/5/28.
 */

public class CollectionAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public List<NewslistBean> mList = new ArrayList<>();

    public CollectionAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_item_two, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        NewslistBean newslistBean = mList.get(position);
        Glide.with(mContext).load(newslistBean.getPicUrl()).placeholder(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(viewHolder.mIv);
        viewHolder.mTv.setText(newslistBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIv;
        private TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv_fragment_home_item);
            mTv = itemView.findViewById(R.id.tv_fragment_home_item);
        }
    }
}
