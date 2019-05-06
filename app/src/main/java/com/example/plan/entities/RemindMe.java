package com.example.plan.entities;

import java.util.Date;

public class RemindMe {
    public long mDate;
    public int mHour;
    public int mMinute;
    public int mSecond;

    public RemindMe(long date, int hour, int minute, int second){
        mDate = date;
        mHour = hour;
        mMinute = minute;
        mSecond = second;
    }

    public long getmDate() {
        return mDate;
    }

    public void setmDate(long mDate) {
        this.mDate = mDate;
    }

    public int getmHour() {
        return mHour;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public int getmMinute() {
        return mMinute;
    }

    public void setmMinute(int mMinute) {
        this.mMinute = mMinute;
    }

    public int getmSecond() {
        return mSecond;
    }

    public void setmSecond(int mSecond) {
        this.mSecond = mSecond;
    }
}
