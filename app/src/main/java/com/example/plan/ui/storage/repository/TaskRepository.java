package com.example.plan.ui.storage.repository;

import com.example.plan.ui.storage.dao.TaskDao;
import com.example.plan.ui.storage.model.TaskItem;

import java.util.List;

public class TaskRepository extends AbsRepository{

    private TaskDao mTaskDao;

    private static TaskRepository mInstance;

    public TaskRepository(TaskDao taskDao){
        mTaskDao = taskDao;
    }

    public static TaskRepository getInstance(TaskDao taskDao){
        if(mInstance == null){
            synchronized (TaskRepository.class){
                if(mInstance == null){
                    mInstance = new TaskRepository(taskDao);
                }
            }
        }

        return mInstance;
    }

    @Override
    public List<TaskItem> loadListItem() {
        return null;
    }

    @Override
    public List<TaskItem> loadListItemWithId(int id) {
        return mTaskDao.getTaskWithID(id);
    }

    @Override
    public long insertItem(Object item) {
        return 0;
    }
}
