package com.example.plan.ui.storage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.plan.ui.storage.model.TaskItem;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<TaskItem> getAllTask();

    @Query("SELECT * FROM task WHERE mListId = :taskId")
    List<TaskItem> getTaskWithID(int taskId);

    @Insert
    long insertTask(TaskItem item);
}
