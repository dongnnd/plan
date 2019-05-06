package com.example.plan.usecase;

import java.util.List;

public interface IDataCallback<T> {
    void dataCallBack(List<T> data);
    void executeSuccess();
    void executeFail();
}
