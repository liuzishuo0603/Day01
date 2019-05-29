package com.example.dell.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dell.myapplication.DetailsActivity;
import com.example.dell.myapplication.R;
import com.example.dell.myapplication.beans.BannerRootBean;
import com.example.dell.myapplication.beans.NewslistBean;
import com.example.dell.myapplication.beans.ResultsBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2019/5/28.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public List<ResultsBean> mBannerList = new ArrayList<>();
    public List<NewslistBean> mArtilcleList = new ArrayList<>();
    private final int ZERO = 0;
    private final int ONE = 1;
    private onClickListener mListener;
    private onLongClickListener mLongClickListener;

    public HomeFragmentAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == ZERO) {
            View view = inflater.inflate(R.layout.home_item_one, null);
            return new MyViewHolder1(view);
        } else {
            View view = inflater.inflate(R.layout.home_item_two, null);
            return new MyViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == ZERO) {
            MyViewHolder1 viewHolder1 = (MyViewHolder1) holder;
            viewHolder1.mBanner.setImages(mBannerList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    ResultsBean resultsBean = (ResultsBean) path;
                    Glide.with(mContext).load(resultsBean.getUrl()).placeholder(R.mipmap.ic_launcher).into(imageView);
                }
            }).setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("url", mBannerList.get(position).getUrl());
                    mContext.startActivity(intent);
                }
            }).start();
        } else {
            if (mBannerList.size() > 0) {
                position = position - 1;
            }
            NewslistBean newslistBean = mArtilcleList.get(position);
            MyViewHolder2 viewHolder2 = (MyViewHolder2) holder;
            Glide.with(mContext).load(newslistBean.getPicUrl()).placeholder(R.mipmap.ic_launcher).apply(RequestOptions.circleCropTransform()).into(viewHolder2.mIv);
            viewHolder2.mTv.setText(newslistBean.getTitle());
            final int finalPosition = position;
            viewHolder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onClick(v, finalPosition);
                    }
                }
            });
            final int finalPosition1 = position;
            viewHolder2.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mLongClickListener != null) {
                        mLongClickListener.onLongClick(v, finalPosition1);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mBannerList.size() > 0) {
            return mArtilcleList.size() + 1;
        } else {
            return mArtilcleList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBannerList.size() > 0 && position == 0) {
            return ZERO;
        } else {
            return ONE;
        }
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {

        private Banner mBanner;

        public MyViewHolder1(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner_fragment_home_item);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {

        private ImageView mIv;
        private TextView mTv;

        public MyViewHolder2(View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv_fragment_home_item);
            mTv = itemView.findViewById(R.id.tv_fragment_home_item);
        }
    }

    public interface onClickListener {
        void onClick(View v, int position);
    }

    public interface onLongClickListener {
        void onLongClick(View v, int position);
    }

    public void setOnClickListener(onClickListener listener) {
        mListener = listener;
    }

    public void setOnLongClickListener(onLongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }
}
