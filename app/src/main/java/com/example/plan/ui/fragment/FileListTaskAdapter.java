package com.example.plan.ui.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.plan.R;
import com.example.plan.ui.storage.model.TaskItem;

import java.util.List;

public class FileListTaskAdapter extends RecyclerView.Adapter<FileListTaskAdapter.FileListViewHolder> {

    private Context mContext;
    private List<TaskItem> mData;
    private LayoutInflater mInflater;

    public FileListTaskAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public FileListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.list_task_item, viewGroup, false);
        return new FileListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FileListViewHolder fileListViewHolder, int i) {
        TaskItem item = mData.get(i);
        fileListViewHolder.mName.setText(item.mName);
    }

    @Override
    public int getItemCount() {
        return (mData != null ? mData.size() : 0);
    }

    public void updateData(List<TaskItem> data){
        mData = data;
        notifyDataSetChanged();
    }

    public class FileListViewHolder extends RecyclerView.ViewHolder{
        private CheckBox mRadio;
        private TextView mName;

        public FileListViewHolder(View view){
            super(view);
            mRadio = view.findViewById(R.id.task_item_radio);
            mName = view.findViewById(R.id.task_item_name);
        }
    }
}
