package com.example.plan.usecase;

import com.example.plan.entities.RemindMe;
import com.example.plan.ui.storage.model.ListPlan;

import java.util.List;

public class AddRemind implements IInteractor<RemindMe>{
    private IRepository mRepository;
    private IThreadExecutor mThread;
    private RemindMe mRemind;
    private IDataCallback<ListPlan> mCallBack;

    public AddRemind(IRepository repository, IThreadExecutor thread, RemindMe remind, IDataCallback<ListPlan> callback){
        mRepository = repository;
        mThread = thread;
        mRemind = remind;
        mCallBack = callback;
    }

    @Override
    public void run() {
        mThread.run(new Runnable() {
            @Override
            public void run() {
                long result = mRepository.insertItem(mRemind);
            }
        });
    }

    @Override
    public void onLoadDataFinish(List<RemindMe> data) {

    }
}
