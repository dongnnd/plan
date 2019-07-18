package com.example.plan.ui.storage.repository;

import com.example.plan.ui.storage.dao.RepeatDao;
import com.example.plan.ui.storage.model.RepeatItem;

import java.util.List;

public class RepeatRepository extends AbsRepository{

    public static RepeatRepository mInstance;
    private RepeatDao mRepeatDao;


    public static RepeatRepository getInstance(RepeatDao repeatDao){
        if(mInstance == null){
            mInstance = new RepeatRepository(repeatDao);
        }

        return mInstance;
    }

    public RepeatRepository(RepeatDao repeatDao){
        mRepeatDao = repeatDao;
    }

    @Override
    public List loadListItemWithId(int id) {
        return null;
    }

    @Override
    public List loadListItem() {
        return null;
    }

    @Override
    public long insertItem(Object item) {
        return mRepeatDao.insertRepeatItem((RepeatItem)item);
    }

    @Override
    public long deleteItem(Object item) {
        return -1;
    }
}
