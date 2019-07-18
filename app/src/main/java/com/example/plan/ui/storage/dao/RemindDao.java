package com.example.plan.ui.storage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.plan.entities.RemindMe;
import com.example.plan.ui.storage.model.RemindItem;

@Dao
public interface RemindDao {

    @Insert
    long insertRemind(RemindItem remindMe);
}
