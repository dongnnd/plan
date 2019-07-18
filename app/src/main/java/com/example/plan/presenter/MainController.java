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
import com.example.plan.ui.storage.model.RemindItem;
import com.example.plan.ui.storage.model.TaskItem;
import com.example.plan.usecase.AddRemind;
import com.example.plan.usecase.AddTask;
import com.example.plan.usecase.IDataCallback;
import com.example.plan.usecase.LoadListPlan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainController extends AbsController implements IDataCallback<ListPlan> {
    private MutableLiveData<Boolean> mStateDrawer = new MutableLiveData<>();
    private MutableLiveData<List<ListPlan>> mData = new MutableLiveData<>();
    private DataForNewTask mDataForNewTak = new DataForNewTask();

    private MutableLiveData<String> mChoosePlan = new MutableLiveData<>();
    private MutableLiveData<String> mChooseDateTime = new MutableLiveData<>();
    private MutableLiveData<String> mChooseRepeat = new MutableLiveData<>();

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
    public void executeSuccess(long id) {
        // refresh data list item

        if(id != -1){
            // insert Repeat
            addRemindToDb((int)id);
            // insert Remind
        }

    }

    public MutableLiveData<String> getmChoosePlan() {
        return mChoosePlan;
    }

    public MutableLiveData<String> getmChooseDateTime() {
        return mChooseDateTime;
    }

    public MutableLiveData<String> getmChooseRepeat() {
        return mChooseRepeat;
    }

    public LiveData<List<ListPlan>> getListPlan(){
        return mData;
    }

    public void addNewTaskToDB(){
        ParaForUseCase para = getParaForUsecase(AppConstants.DataType.TASK);
        TaskItem taskItem = new TaskItem(mDataForNewTak.mName, false, null, mDataForNewTak.mIdListTask);
        AddTask addTask = new AddTask(para.mRepository, para.mThread, taskItem, this);
        addTask.run();

    }

    public void addRemindToDb(int id){
        ParaForUseCase para = getParaForUsecase(AppConstants.DataType.REMIND);
        RemindItem remindItem = new RemindItem(mDataForNewTak.mRemindMe.mDate, mDataForNewTak.mRemindMe.mHour, mDataForNewTak.mRemindMe.mMinute, mDataForNewTak.mRemindMe.mSecond);
        remindItem.setmTaskId(id);
        AddRemind addTask = new AddRemind(para.mRepository, para.mThread, remindItem, this);
        addTask.run();
    }

    public DataForNewTask getCurrentDataNewTask(){
        return mDataForNewTak;
    }

    public class DataForNewTask{
        private int mIdListTask;
        private String mName;

        private Repeat mRepeat;
        private RemindMe mRemindMe;

        public DataForNewTask(){
            reset();
        }

        public void reset(){
            mRemindMe = new RemindMe();
            mRepeat = new Repeat();
        }


        public void setmPlan(ListPlan plan) {
            mIdListTask = plan.getmId();
            mChoosePlan.setValue(plan.getmName());
        }

        public Repeat getmRepeat() {
            return mRepeat;
        }

        public void setmRepeat(Repeat mRepeat, String str) {
            this.mRepeat = mRepeat;
            mChooseRepeat.setValue(str);
        }

        public RemindMe getmRemindMe() {
            return mRemindMe;
        }

        public void setmRemindMe(RemindMe mRemindMe) {
            this.mRemindMe = mRemindMe;
        }

        public void updateBtnRemind(){
            mChooseDateTime.setValue(getStringForBtnRemind());
        }

        private String getStringForBtnRemind(){
            String result = "";
            String pattern = "MM/dd/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            String date = df.format(mRemindMe.getmDate());
            result += mRemindMe.getmHour() + ":" +mRemindMe.getmMinute() + "\n";
            result += date;
            return result;
        }

        public void setName(String name){
            mName = name;
        }
    }

}
