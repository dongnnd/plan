package com.example.plan.ui.storage.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.plan.ui.storage.model.ListPlan;

import java.util.List;

@Dao
public interface ListPlanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertListPlan(ListPlan listPlan);

    @Query("SELECT * FROM list_plan")
    List<ListPlan> getAllListPlan();

    @Query("SELECT * FROM list_plan WHERE mName = :name")
    ListPlan getDuplicateListPlan(String name);
}
