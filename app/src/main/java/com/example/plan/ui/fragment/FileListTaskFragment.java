package com.example.plan.ui.fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plan.R;
import com.example.plan.presenter.FileListController;
import com.example.plan.ui.storage.model.TaskItem;

import java.util.List;

public class FileListTaskFragment extends Fragment implements ILoading{

    public static final String LIST_TASK_ID = "list_task_id";

    private FileListController mController;
    private int mIdListTask = -1;

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
        startLoading();
    }

    private void observerDataChange(){
        mController.getData().observe(this, new Observer<List<TaskItem>>() {
            @Override
            public void onChanged(@Nullable List<TaskItem> taskItems) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.file_list_task, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void startLoading() {
        if(mIdListTask != -1){
            mController.loadListTaskWithID(mIdListTask);
        }
    }
}
