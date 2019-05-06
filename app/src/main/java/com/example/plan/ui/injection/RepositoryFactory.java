package com.example.plan.ui.injection;

import android.content.Context;

import com.example.plan.presenter.contant.AppConstants;
import com.example.plan.ui.storage.AppDatabase;
import com.example.plan.ui.storage.dao.ListPlanDao;
import com.example.plan.ui.storage.repository.AbsRepository;
import com.example.plan.ui.storage.repository.ListPlanRepository;
import com.example.plan.ui.storage.repository.TaskRepository;

public class RepositoryFactory {

    public static AbsRepository getRepositoryByType(Context context, int typeData) {
        context = context.getApplicationContext();
        AppDatabase database = AppDatabase.getInstance(context);
        AbsRepository repository = null;
        switch (typeData) {
            case AppConstants
                    .DataType.LIST_PLAN:
                repository = ListPlanRepository.getInstance(database.getListPlanDao());
                break;
            case AppConstants
                    .DataType.REMIND:
                break;
            case AppConstants
                    .DataType.TASK:
                repository = TaskRepository.getInstance(database.getTaskDao());
                break;
            case AppConstants
                    .DataType.REPEAT_ME:
                break;
        }
        return repository;
    }

}
