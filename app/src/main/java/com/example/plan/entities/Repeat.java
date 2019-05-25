package com.example.plan.entities;

public class Repeat {
    public int mNone;
    public int mMon;
    public int mTue;
    public int mWed;
    public int mThurs;
    public int mFri;
    public int Sat;
    public int Sun;

    public Repeat(int none){
        mNone = none;
    }

    public Repeat(){

    }

    public int getmNone() {
        return mNone;
    }

    public void setmNone(int mNone) {
        this.mNone = mNone;
    }

    public int getmMon() {
        return mMon;
    }

    public void setmMon(int mMon) {
        this.mMon = mMon;
    }

    public int getmTue() {
        return mTue;
    }

    public void setmTue(int mTue) {
        this.mTue = mTue;
    }

    public int getmWed() {
        return mWed;
    }

    public void setmWed(int mWed) {
        this.mWed = mWed;
    }

    public int getmThurs() {
        return mThurs;
    }

    public void setmThurs(int mThurs) {
        this.mThurs = mThurs;
    }

    public int getmFri() {
        return mFri;
    }

    public void setmFri(int mFri) {
        this.mFri = mFri;
    }

    public int getSat() {
        return Sat;
    }

    public void setSat(int sat) {
        Sat = sat;
    }

    public int getSun() {
        return Sun;
    }

    public void setSun(int sun) {
        Sun = sun;
    }
}
