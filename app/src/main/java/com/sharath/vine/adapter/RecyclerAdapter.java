package com.sharath.vine.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharath.vine.R;
import com.sharath.vine.modell.HotKeys;
import com.sharath.vine.modell.WHProducts;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HORIZONTAL_SCROLL_ROW = 362;
    private static final int NORMAL_ROW = 366;

    private ArrayList<WHProducts> homeList, featureList;
    private ArrayList<HotKeys> hotKeyList;

    private LayoutInflater layoutInflater;

    public RecyclerAdapter(Activity context, ArrayList<WHProducts> homeList, ArrayList<WHProducts> featuredList,
                           ArrayList<HotKeys> hotKeyList, int totalPages) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.homeList = homeList;
        this.featureList = featuredList;
        this.hotKeyList = hotKeyList;

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return HORIZONTAL_SCROLL_ROW;
            case 1:
                return HORIZONTAL_SCROLL_ROW;
            default:
                return NORMAL_ROW;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HORIZONTAL_SCROLL_ROW:
                /*View view = layoutInflater.inflate(R.layout.horizontal_row, parent,
                        false);
                return new HorizontalViewHolder(view);*/
            default:
                View view = layoutInflater.inflate(R.layout.normal_row, parent,
                        false);
                return new NormalViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return homeList.size()+2;
    }

    private static class HorizontalViewHolder extends RecyclerView.ViewHolder {
        private HorizontalScrollView horizontalScrollView;
        private LinearLayout layoutHolder;

        HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public HorizontalScrollView getHorizontalScrollView() {
            if (horizontalScrollView == null) {
                horizontalScrollView = itemView.findViewById(R.id.horizontalScrollView);
            }
            return horizontalScrollView;
        }

        public LinearLayout getLayoutHolder() {
            if (layoutHolder == null) {
                layoutHolder = itemView.findViewById(R.id.layoutHolder);
            }
            return layoutHolder;
        }
    }

    private static class NormalViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        NormalViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public TextView getTextView() {
            if (textView == null) {
                textView = itemView.findViewById(R.id.txtView);
            }
            return textView;
        }
    }
}
