package com.example.plan.usecase;

import com.example.plan.entities.Task;
import com.example.plan.ui.storage.model.ListPlan;

import java.util.List;

public class AddTask implements IInteractor<Task>{
    private IRepository mRepository;
    private IThreadExecutor mThread;
    private Task mTask;
    private IDataCallback<ListPlan> mCallBack;

    public AddTask(IRepository repository, IThreadExecutor thread, Task task, IDataCallback callback){
        mRepository = repository;
        mThread = thread;
        mTask = task;
        mCallBack = callback;
    }


    @Override
    public void run() {
        mThread.run(new Runnable() {
            @Override
            public void run() {
                long result = mRepository.insertItem(mTask);
                if(result > 0 ){
                    mCallBack.executeSuccess(result);
                }else{
                    mCallBack.executeFail();
                }
            }
        });
    }

    @Override
    public void onLoadDataFinish(List<Task> data) {

    }
}
