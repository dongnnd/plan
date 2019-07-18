package com.example.plan.ui.storage.repository;

import com.example.plan.entities.RemindMe;
import com.example.plan.ui.storage.dao.RemindDao;
import com.example.plan.ui.storage.model.RemindItem;

import java.util.List;

public class RemindRepository extends AbsRepository{

    public static RemindRepository mInstance;
    private RemindDao mRemindDao;

    public static RemindRepository getInstance(RemindDao remindDao){
        if(mInstance == null){
            mInstance = new RemindRepository(remindDao);
        }

        return mInstance;
    }

    public RemindRepository(RemindDao remindDao){
        mRemindDao = remindDao;
    }

    @Override
    public List loadListItem() {
        return null;
    }

    @Override
    public List loadListItemWithId(int id) {
        return null;
    }

    @Override
    public long insertItem(Object item) {
        return mRemindDao.insertRemind((RemindItem) item);
    }

    @Override
    public void deleteItem(Object item) {

    }
}
