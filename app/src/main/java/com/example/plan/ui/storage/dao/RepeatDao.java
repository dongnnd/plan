package com.example.plan.ui.storage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.plan.entities.Repeat;
import com.example.plan.ui.storage.model.RepeatItem;

@Dao
public interface RepeatDao {

    @Insert
    long insertRepeatItem(RepeatItem repeatItem);
}
