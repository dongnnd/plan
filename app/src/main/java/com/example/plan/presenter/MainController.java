package com.example.plan.presenter;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.example.plan.entities.RemindMe;
import com.example.plan.entities.Repeat;
import com.example.plan.entities.Task;
import com.example.plan.presenter.contant.AppConstants;
import com.example.plan.ui.storage.model.ListPlan;
import com.example.plan.usecase.IDataCallback;
import com.example.plan.usecase.LoadListPlan;

import java.util.List;

public class MainController extends AbsController implements IDataCallback<ListPlan> {
    private MutableLiveData<Boolean> mStateDrawer = new MutableLiveData<>();
    private MutableLiveData<List<ListPlan>> mData = new MutableLiveData<>();
    private DataForNewTask mDataForNewTak = new DataForNewTask();

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
        ParaForUseCase para = getParaForUsecase(AppConstants.DataType.LIST_PLAN);
        LoadListPlan addList = new LoadListPlan(para.mRepository, para.mThread, this);
        addList.run();
    }

    @Override
    public void dataCallBack(List<ListPlan> data) {
        mData.postValue(data);
    }

    @Override
    public void executeFail() {

    }

    @Override
    public void executeSuccess() {

    }

    public LiveData<List<ListPlan>> getListPlan(){
        return mData;
    }

    public void addNewTaskToDB(){
        Log.d("dong.nd1", mDataForNewTak.mRemindMe.mDate + " | " + mDataForNewTak.getmRemindMe().getmHour() + ":" + mDataForNewTak.getmRemindMe().getmMinute());
    }

    public DataForNewTask getCurrentDataNewTask(){
        return mDataForNewTak;
    }

    public class DataForNewTask{
        private Task mTask;
        private Repeat mRepeat;
        private RemindMe mRemindMe;

        public DataForNewTask(){
            reset();
        }

        public void reset(){
            mTask = new Task();
            mRemindMe = new RemindMe();
            mRepeat = new Repeat();
        }

        public Task getmTask() {
            return mTask;
        }

        public void setmTask(Task mTask) {
            this.mTask = mTask;
        }

        public Repeat getmRepeat() {
            return mRepeat;
        }

        public void setmRepeat(Repeat mRepeat) {
            this.mRepeat = mRepeat;
        }

        public RemindMe getmRemindMe() {
            return mRemindMe;
        }

        public void setmRemindMe(RemindMe mRemindMe) {
            this.mRemindMe = mRemindMe;
        }
    }
}
