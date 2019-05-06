package com.example.plan.ui.storage.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.plan.entities.RemindMe;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "remind", foreignKeys = @ForeignKey(entity = TaskItem.class, parentColumns = "mId", childColumns =
        "mRemindId", onDelete = CASCADE))
public class RemindItem extends RemindMe {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    public int mRemindId;

    public RemindItem(long date, int hour, int minute, int second) {
        super(date, hour, minute, second);
    }

    public int getmRemindId() {
        return mRemindId;
    }

    public void setmRemindId(int mRemindId) {
        this.mRemindId = mRemindId;
    }
}
