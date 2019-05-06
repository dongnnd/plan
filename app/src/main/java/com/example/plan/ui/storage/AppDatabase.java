package com.example.plan.ui.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.plan.ui.storage.dao.ListPlanDao;
import com.example.plan.ui.storage.dao.RemindDao;
import com.example.plan.ui.storage.dao.RepeatDao;
import com.example.plan.ui.storage.dao.TaskDao;
import com.example.plan.ui.storage.model.ListPlan;
import com.example.plan.ui.storage.model.RemindItem;
import com.example.plan.ui.storage.model.RepeatItem;
import com.example.plan.ui.storage.model.TaskItem;

@Database(entities = {ListPlan.class, RemindItem.class, TaskItem.class, RepeatItem.class}, version = 1,  exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase mInstance;
    private static final String DB_NAME = "plan";

    public abstract ListPlanDao getListPlanDao();
    public abstract RemindDao getRemindDao();
    public abstract RepeatDao getRepeatDao();
    public abstract TaskDao getTaskDao();

    public static AppDatabase getInstance(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return mInstance;
    }
}
