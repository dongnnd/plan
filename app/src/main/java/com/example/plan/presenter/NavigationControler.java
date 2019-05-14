package com.example.plan.presenter;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.plan.presenter.contant.AppConstants;
import com.example.plan.ui.storage.model.ListPlan;
import com.example.plan.usecase.AddListPlan;
import com.example.plan.usecase.IDataCallback;
import com.example.plan.usecase.LoadListPlan;

import java.util.List;

public class NavigationControler extends AbsController implements IDataCallback<ListPlan> {

    private MutableLiveData<List<ListPlan>> mListData = new MutableLiveData<>();

    public NavigationControler(Application application) {
        super(application);
    }

    public LiveData<List<ListPlan>> getListData(){
        return mListData;
    }

    @Override
    public void dataCallBack(List<ListPlan> data) {
        mListData.postValue(data);
    }

    public void loadNavigationItem(){
        ParaForUseCase para = getParaForUsecase(AppConstants.DataType.LIST_PLAN);
        LoadListPlan addList = new LoadListPlan(para.mRepository, para.mThread, this);
        addList.run();
    }

    public void addListPlan(String name){
        ParaForUseCase para = getParaForUsecase(AppConstants.DataType.LIST_PLAN);
        AddListPlan addList = new AddListPlan(para.mRepository, para.mThread, name, this);
        addList.run();
    }

    @Override
    public void executeFail() {

    }

    @Override
    public void executeSuccess() {
        loadNavigationItem();
    }
}
