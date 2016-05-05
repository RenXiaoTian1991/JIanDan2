package com.example.lenovo.jiandan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.jiandan.Bean.FreshNews;
import com.example.lenovo.jiandan.R;

import java.io.PipedOutputStream;
import java.text.FieldPosition;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/1/26.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<FreshNews> mDatas;
    private final static int VIEW_FOOT = 0;
    private final static int VIEW_ITEM = 1;
    private Context mContext;

    public RecycleAdapter(ArrayList<FreshNews> mDatas,Context context) {
        this.mDatas = mDatas;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_ITEM){
            View view = View.inflate(mContext, R.layout.item_recycle,null);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }else if(viewType ==VIEW_FOOT){
              View view = View.inflate(mContext,R.layout.item_foot,null);
              FootViewHolder holder = new FootViewHolder(view);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
                if(holder instanceof MyViewHolder){
                    FreshNews data = mDatas.get(position);
                    ((MyViewHolder) holder).txt_type.setText(data.getDate());
                    ((MyViewHolder) holder).txt_head.setText(data.getTitle());
                }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mDatas.size()){
            return VIEW_FOOT;
        }else{
            return VIEW_ITEM;
        }
    }

    public void setData(ArrayList<FreshNews> data){
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private View view;
        public TextView txt_head,txt_type;
        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            txt_head = (TextView) view.findViewById(R.id.item_txt);
            txt_type = (TextView) view.findViewById(R.id.iten_type);
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder{
        private View view;
        public FootViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

}
