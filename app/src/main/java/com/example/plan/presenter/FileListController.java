package com.example.plan.presenter;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.plan.presenter.contant.AppConstants;
import com.example.plan.ui.storage.model.TaskItem;
import com.example.plan.usecase.IDataCallback;
import com.example.plan.usecase.LoadListTask;

import java.util.List;

public class FileListController extends AbsController implements IDataCallback<TaskItem> {

    private MutableLiveData<List<TaskItem>> mData = new MutableLiveData<>();

    public FileListController(Application application) {
        super(application);
    }

    public LiveData<List<TaskItem>> getData() {
        return mData;
    }

    public void loadListTaskWithID(int id) {
        ParaForUseCase para = getParaForUsecase(AppConstants.DataType.TASK);
        LoadListTask listTask = new LoadListTask(para.mRepository, para.mThread, this, id);
        listTask.run();
    }

    @Override
    public void dataCallBack(List<TaskItem> data) {
        Log.d("dong.nd1", "new use case: " + data.size());
    }

    @Override
    public void executeSuccess() {

    }

    @Override
    public void executeFail() {

    }
}
