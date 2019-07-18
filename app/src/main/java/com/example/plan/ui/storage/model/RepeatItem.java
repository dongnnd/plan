package com.example.plan.ui.storage.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.plan.entities.Repeat;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "repeat", foreignKeys = @ForeignKey(entity = TaskItem.class, parentColumns = "mId", childColumns =
        "mTaskId", onDelete = CASCADE))
public class RepeatItem extends Repeat {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    public int mTaskId;

    public RepeatItem(int none){
        super(none);
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmTaskId() {
        return mTaskId;
    }

    public void setmTaskId(int mTaskId) {
        this.mTaskId = mTaskId;
    }
}
