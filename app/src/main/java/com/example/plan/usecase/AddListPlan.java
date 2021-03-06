package com.example.plan.usecase;

import android.util.Log;

import com.example.plan.ui.storage.model.ListPlan;

import java.util.List;

public class AddListPlan implements IInteractor<ListPlan>{
    private IRepository mRepository;
    private IThreadExecutor mThread;
    private String mName;
    private IDataCallback<ListPlan> mCallBack;

    public AddListPlan(IRepository repository, IThreadExecutor thread, String name, IDataCallback<ListPlan> callback){
        mRepository = repository;
        mThread = thread;
        mName = name;
        mCallBack = callback;
    }

    @Override
    public void run() {
        mThread.run(new Runnable() {
            @Override
            public void run() {
                ListPlan newPlan = new ListPlan(mName);
                long result =  mRepository.insertItem(newPlan);
                Log.d("dong.nd1", "Result: " + result);
                mCallBack.executeSuccess(result);
            }
        });
    }

    @Override
    public void onLoadDataFinish(List<ListPlan> data) {

    }
}
