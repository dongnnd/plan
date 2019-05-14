package com.example.plan.ui.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plan.R;
import com.example.plan.ui.dialog.IChooseItem;
import com.example.plan.ui.storage.model.ListPlan;

import java.util.List;

public class ChoosePlanAdapter extends RecyclerView.Adapter<ChoosePlanAdapter.ChooseListHolder> {

    private Context mContext;
    private List<ListPlan> mData;
    private IChooseItem mClickCallBack;

    public void setClickCallBack(IChooseItem callBack){
        mClickCallBack = callBack;
    }

    public ChoosePlanAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public ChooseListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.choose_plan_item, viewGroup, false);
        return  new ChooseListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseListHolder chooseListHolder,final int i) {
        ListPlan item = mData.get(i);
        chooseListHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickCallBack.onItemClick(mData.get(i));
            }
        });
        chooseListHolder.mName.setText(item.getmName());
    }

    @Override
    public int getItemCount() {
        return (mData != null ? mData.size() : 0);
    }

    public class ChooseListHolder extends RecyclerView.ViewHolder{
        private TextView mName;

        public ChooseListHolder(View v){
            super(v);
            mName = v.findViewById(R.id.choose_item_name);
        }
    }

    public void updateData(List<ListPlan> data){
        mData = data;
        notifyDataSetChanged();
    }
}
