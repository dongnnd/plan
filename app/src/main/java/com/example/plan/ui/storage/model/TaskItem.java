package com.example.plan.ui.storage.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.plan.entities.Task;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "task", foreignKeys = @ForeignKey(entity = ListPlan.class, parentColumns = "mId",
        childColumns = "mListId", onDelete = CASCADE))
public class TaskItem extends Task {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    public TaskItem(String name, boolean isCompleted, String note, int idListPlan) {
        super(name, isCompleted, note, idListPlan);
    }

    public TaskItem(){

    }
}
