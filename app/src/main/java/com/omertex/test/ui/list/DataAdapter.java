package com.omertex.test.ui.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.omertex.moovies.R;
import com.omertex.test.model.Data;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataVH> {

    private List<Data> items = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener {

        void onItemClick(Data data);
    }

    DataAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    void setItems(List<Data> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public DataVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);

        return new DataVH(view);
    }

    @Override
    public void onBindViewHolder(final DataVH holder, final int position) {
        final Data item = getItem(position);
        Glide.with(holder.ivThumbnail).load(item.getImageUrl()).into(holder.ivThumbnail);
        holder.tvName.setText(item.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(items.get(position));
            }
        });
    }

    private Data getItem(int position) {
        return items.get(position);
    }

    static class DataVH extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_thumbnail)
        ImageView ivThumbnail;

        @BindView(R.id.tv_name)
        TextView tvName;

        View view;

        DataVH(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }
}
