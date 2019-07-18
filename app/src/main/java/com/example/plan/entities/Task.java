package com.example.plan.entities;

public class Task {
    public String mName;
    public boolean mIsCompleted;
    public String mNote;
    public int mListId;

    public Task(String name, boolean isCompleted, String note, int idListPlan) {
        mName = name;
        mIsCompleted = isCompleted;
        mNote = note;
        mListId = idListPlan;
    }

    public Task(){

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean ismIsCompleted() {
        return mIsCompleted;
    }

    public void setmIsCompleted(boolean mIsCompleted) {
        this.mIsCompleted = mIsCompleted;
    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }

    public int getmListId() {
        return mListId;
    }

    public void setmListId(int mListId) {
        this.mListId = mListId;
    }
}
