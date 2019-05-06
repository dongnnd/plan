package com.example.plan.entities;

public class Task {
    public String mName;
    public boolean mIsCompleted;
    public int mRepeat;
    public String mNote;

    public Task(String name, boolean isCompleted, int repeat, String note) {
        mName = name;
        mIsCompleted = isCompleted;
        mRepeat = repeat;
        mNote = note;
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


    public int getmRepeat() {
        return mRepeat;
    }

    public void setmRepeat(int mRepeat) {
        this.mRepeat = mRepeat;
    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }
}
