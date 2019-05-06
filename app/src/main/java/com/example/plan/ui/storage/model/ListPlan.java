package com.example.plan.ui.storage.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "list_plan")
public class ListPlan {

    @PrimaryKey(autoGenerate = true)
    public int mId;
    public String mName;

    public ListPlan(String name){
        mName = name;
    }

    public ListPlan(){

    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
