package com.example.plan.usecase;

import java.util.List;

public interface IRepository<T> {

    List<T> loadListItem();

    List<T> loadListItemWithId(int id);

    long insertItem(T item);

    long deleteItem(T item);
}
