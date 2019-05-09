package com.example.plan.presenter;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.plan.ui.storage.model.ListPlan;
import com.example.plan.usecase.IDataCallback;

import java.util.List;

public class MainController extends AbsController implements IDataCallback<ListPlan> {
    private MutableLiveData<Boolean> mStateDrawer = new MutableLiveData<>();

    public MainController(Application application){
        super(application);
    }

    public LiveData<Boolean> getStateDrawer(){
        return mStateDrawer;
    }

    public void setValueStateDrawer(boolean value){
        mStateDrawer.setValue(value);
    }

    public void loadListPlan(){

    }

    @Override
    public void dataCallBack(List<ListPlan> data) {

    }

    @Override
    public void executeFail() {

    }

    @Override
    public void executeSuccess() {

    }
}
