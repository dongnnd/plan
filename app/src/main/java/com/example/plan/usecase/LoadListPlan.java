package com.example.plan.usecase;

import com.example.plan.ui.storage.model.ListPlan;

import java.util.List;

public class LoadListPlan implements IInteractor<ListPlan> {

    private IRepository mRepository;
    private IThreadExecutor mThread;
    private IDataCallback<ListPlan> mDataCallBack;

    public LoadListPlan(IRepository repository, IThreadExecutor thread, IDataCallback<ListPlan> callback){
        mRepository = repository;
        mThread = thread;
        mDataCallBack = callback;
    }


    @Override
    public void run() {
        mThread.run(new Runnable() {
            @Override
            public void run() {
                List<ListPlan> data = mRepository.loadListItem();
                onLoadDataFinish(data);
            }
        });
    }

    @Override
    public void onLoadDataFinish(List<ListPlan> data) {
        mDataCallBack.dataCallBack(data);
    }


}
