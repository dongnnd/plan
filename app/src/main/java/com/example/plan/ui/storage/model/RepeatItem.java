package com.example.plan.ui.storage.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.plan.entities.Repeat;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "repeat", foreignKeys = @ForeignKey(entity = TaskItem.class, parentColumns = "mId", childColumns =
        "mRepeatId", onDelete = CASCADE))
public class RepeatItem extends Repeat {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    public int mRepeatId;

    public RepeatItem(int repeatType){
        super(repeatType);
    }

    public int getmRepeatId() {
        return mRepeatId;
    }

    public void setmRepeatId(int mRepeatId) {
        this.mRepeatId = mRepeatId;
    }
}
