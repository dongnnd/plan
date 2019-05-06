package com.example.plan.ui.threadexcutor;

import com.example.plan.usecase.IThreadExecutor;

public class LoadDataThread implements IThreadExecutor {

    @Override
    public void run(Runnable runnable) {
        new Thread(runnable).start();
    }
}
