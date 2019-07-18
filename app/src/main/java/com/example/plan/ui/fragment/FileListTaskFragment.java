package com.example.plan.ui.fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plan.R;
import com.example.plan.presenter.FileListController;
import com.example.plan.ui.storage.model.ListPlan;
import com.example.plan.ui.storage.model.TaskItem;

import java.util.List;

public class FileListTaskFragment extends Fragment implements INavigationChange{

    public static final String LIST_TASK_ID = "list_task_id";

    private FileListController mController;
    private FileListTaskAdapter mAdapter;
    private int mIdListTask = -1;
    private View mRootView;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        mIdListTask = args.getInt(LIST_TASK_ID);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = new FileListController(getActivity().getApplication());
        observerDataChange();
        mController.loadListTaskWithID(1);
    }

    private void observerDataChange(){
        mController.getData().observe(this, new Observer<List<TaskItem>>() {
            @Override
            public void onChanged(@Nullable List<TaskItem> taskItems) {
                mAdapter.updateData(taskItems);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.file_list_task, container, false);

        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        RecyclerView listIteam = mRootView.findViewById(R.id.file_list_task);
        mAdapter = new FileListTaskAdapter(getContext());
        listIteam.setAdapter(mAdapter);
        listIteam.setLayoutManager(new LinearLayoutManager(getContext()));
        listIteam.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    public void loadingTaskList(int idListTask){
        mController.loadListTaskWithID(idListTask);
    }

    @Override
    public void onNavigationChange(ListPlan listPlan) {
        loadingTaskList(listPlan.getmId());
    }
}
