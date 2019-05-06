package com.example.plan.ui.storage.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.plan.entities.Task;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "task", foreignKeys = @ForeignKey(entity = ListPlan.class, parentColumns = "mId",
        childColumns = "mTaskId", onDelete = CASCADE))
public class TaskItem extends Task {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    public int mTaskId;

    public TaskItem(String name, boolean isCompleted, int repeat, String note) {
        super(name, isCompleted, repeat, note);
    }

    public int getmTaskId() {
        return mTaskId;
    }

    public void setmTaskId(int mTaskId) {
        this.mTaskId = mTaskId;
    }
}
