package com.example.plan.usecase;

import java.util.List;

public interface IInteractor<T> {

    void run();

    void onLoadDataFinish(List<T> data);
}
