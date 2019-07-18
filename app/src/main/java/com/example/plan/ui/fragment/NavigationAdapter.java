package com.example.plan.ui.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.plan.R;
import com.example.plan.ui.storage.model.ListPlan;

import java.util.ArrayList;
import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavHolder> {

    private List<ListPlan> mData = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflate;
    private IItemAction mActionCallBack;
    private IItemAction mItemClick;

    public interface IItemAction {
        void itemClick(ListPlan item);

        void deleteItem(ListPlan item);
    }

    public void setItemActionCallBack(IItemAction itemAction) {
        mActionCallBack = itemAction;
    }

    public NavigationAdapter(Context context, IItemAction itemClick) {
        mContext = context;
        mInflate = LayoutInflater.from(mContext);
        mItemClick = itemClick;
    }


    @NonNull
    @Override
    public NavHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = mInflate.inflate(R.layout.nav_item, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActionCallBack.itemClick(mData.get(i));
            }
        });
        return new NavHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NavHolder navHolder, final int i) {

        final ListPlan item = mData.get(i);
        navHolder.mName.setText(item.getmName());
        navHolder.mCount.setText("1");
        navHolder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClick.deleteItem(item);
            }
        });
        navHolder.mItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClick.itemClick(item);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void updateDataChange(List<ListPlan> data) {
        mData = data;
        if (mData.size() > 1) {
            mData.remove(0);
            mData.remove(0);
        }
        //addEmptyItem();
        notifyDataSetChanged();
    }

    public class NavHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mCount;
        private ImageView mDelete;
        private LinearLayout mItemContainer;

        public NavHolder(View view) {
            super(view);
            mName = view.findViewById(R.id.nav_item_name);
            mCount = view.findViewById(R.id.nav_item_count);
            mDelete = view.findViewById(R.id.nav_item_delete);
            mItemContainer = view.findViewById(R.id.nav_item_container);
        }
    }

    private void addEmptyItem() {
        for (int i = 0; i < 5; i++) {
            mData.add(new ListPlan());
        }
    }

}
