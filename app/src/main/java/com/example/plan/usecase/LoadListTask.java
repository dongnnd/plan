package com.example.plan.usecase;

import com.example.plan.entities.Task;
import com.example.plan.ui.storage.model.TaskItem;

import java.util.List;

public class LoadListTask implements IInteractor<TaskItem>{

    private IRepository mRepository;
    private IThreadExecutor mThread;
    private IDataCallback mCallBack;
    private int mLoadTaskId;

    public LoadListTask(IRepository repository, IThreadExecutor thread, IDataCallback callback, int loadTaskId){
        mRepository = repository;
        mThread = thread;
        mCallBack = callback;
        mLoadTaskId = loadTaskId;
    }

    @Override
    public void run() {
        mThread.run(new Runnable() {
            @Override
            public void run() {
                List<TaskItem> data = mRepository.loadListItemWithId(mLoadTaskId);
                mCallBack.dataCallBack(data);
            }
        });
    }

    @Override
    public void onLoadDataFinish(List<TaskItem> data) {

    }
}
