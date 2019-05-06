package com.example.plan.presenter;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

public class MainController extends AbsController{
    private MutableLiveData<Boolean> mStateDrawer = new MutableLiveData<>();
    public boolean mIsShowFloatingButton = true;
    private

    public MainController(Application application){
        super(application);
    }

    public LiveData<Boolean> getStateDrawer(){
        return mStateDrawer;
    }

    public void setValueStateDrawer(boolean value){
        mStateDrawer.setValue(value);
    }

    public void showBottomAddTask(){

    }

    public boolean ismIsShowFloatingButton() {
        return mIsShowFloatingButton;
    }

    public void setmIsShowFloatingButton(boolean value) {
        this.mIsShowFloatingButton = value;
    }

}
