package com.example.plan.presenter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import com.example.plan.entities.RemindMe;
import com.example.plan.entities.Repeat;
import com.example.plan.entities.Task;
import com.example.plan.presenter.contant.DataLoader;
import com.example.plan.ui.injection.RepositoryFactory;
import com.example.plan.ui.storage.model.ListPlan;
import com.example.plan.ui.threadexcutor.LoadDataThread;
import com.example.plan.usecase.IRepository;
import com.example.plan.usecase.IThreadExecutor;

import java.util.HashMap;
import java.util.Timer;

public abstract class AbsController extends AndroidViewModel {

    public Context mContext;
    public IRepository mRepository;
    public DataLoader mDataLoader;
    public HashMap<Integer, IRepository> mRepositoryMapp = new HashMap<>();

    public AbsController(Application application) {
        super(application);
        mContext = application.getApplicationContext();
    }


    public ParaForUseCase getParaForUsecase(int dataType) {
        return  new ParaForUseCase(RepositoryFactory.getRepositoryByType(mContext, dataType), new LoadDataThread());
    }

    public class ParaForUseCase {
        public IRepository mRepository;
        public IThreadExecutor mThread;

        public ParaForUseCase(IRepository repository, IThreadExecutor thread) {
            mRepository = repository;
            mThread = thread;
        }

    }

    public class ParaForNewTask{
        public int mIdListPlan;
        public Task mTask;
        public RemindMe mRemind;
        public Repeat mRepeat;

//        public ParaForNewTask(IRepository repository, IThreadExecutor thread, Task task, RemindMe remind, Repeat repeat){
//            mRepository = repository;
//            mThread = thread;
//        }
    }

    /*private Dao getDaoType(int dataType) {
        AppDatabase database = AppDatabase.getInstance(mContext);
        Dao dao = null;
        switch (dataType) {
            case AppConstants
                    .DataType.LIST_PLAN:
                (ListPlanDao)dao = database.getListPlanDao();
                break;
            case AppConstants
                    .DataType.REMIND:
                break;
            case AppConstants
                    .DataType.TASK:
                break;
            case AppConstants
                    .DataType.REPEAT_ME:
                break;
        }
        return  null
    }*/

}
