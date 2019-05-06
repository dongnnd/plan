package com.example.plan.presenter.contant;

import java.util.List;

public class DataLoader {

    public interface DataLoaderCallback<T>{
        void onLoadFinish(List<T> data);
    }
}
