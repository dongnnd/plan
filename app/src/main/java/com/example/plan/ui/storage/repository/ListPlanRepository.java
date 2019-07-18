package com.example.plan.ui.storage.repository;

import com.example.plan.ui.storage.dao.ListPlanDao;
import com.example.plan.ui.storage.model.ListPlan;
import com.example.plan.usecase.IRepository;

import java.util.List;

public class ListPlanRepository extends AbsRepository {

    private ListPlanDao mListPlanDao;
    private static ListPlanRepository mInstance;

    public ListPlanRepository(ListPlanDao listPlanDao){
        mListPlanDao = listPlanDao;
    }

    public static ListPlanRepository getInstance(ListPlanDao listPlanDao){
        if(mInstance == null){
            synchronized (ListPlanRepository.class){
                if(mInstance == null){
                    mInstance = new ListPlanRepository(listPlanDao);
                }
            }
        }
        return mInstance;
    }

    @Override
    public List<ListPlan> loadListItem() {
        return mListPlanDao.getAllListPlan();
    }

    @Override
    public long insertItem(Object item) {
        ListPlan plan = (ListPlan)item;
        return mListPlanDao.insertListPlan(plan);
    }

    public boolean checkDuplicatePlan(String name){
        if(mListPlanDao.getDuplicateListPlan(name) != null){
            return true;
        }
        return false;
    }

    @Override
    public List loadListItemWithId(int id) {
        return null;
    }

    @Override
    public long deleteItem(Object item) {
        return mListPlanDao.deleteItem((ListPlan)item);
    }
}

