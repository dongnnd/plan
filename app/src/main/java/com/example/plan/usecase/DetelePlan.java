package com.example.plan.usecase;

import android.media.Image;

import com.example.plan.entities.Task;
import com.example.plan.ui.storage.model.ListPlan;

import java.util.List;

public class DetelePlan implements IInteractor<ListPlan>{
    private IRepository mRepository;
    private IThreadExecutor mThread;
    private ListPlan mPlan;
    private IDataCallback<ListPlan> mCallBack;

    public DetelePlan(IRepository repository, IThreadExecutor thread, ListPlan plan, IDataCallback callback){
        mRepository = repository;
        mThread = thread;
        mCallBack = callback;
        mPlan = plan;
    }

    @Override
    public void run() {
        mThread.run(new Runnable() {
            @Override
            public void run() {
                long result = mRepository.deleteItem(mPlan);
                mCallBack.executeSuccess(result);
            }
        });
    }

    @Override
    public void onLoadDataFinish(List<ListPlan> data) {
        mCallBack.dataCallBack(data);
    }
}
