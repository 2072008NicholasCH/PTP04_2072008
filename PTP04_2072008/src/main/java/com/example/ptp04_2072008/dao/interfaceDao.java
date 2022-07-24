package com.example.ptp04_2072008.dao;

import javafx.collections.ObservableList;

public interface interfaceDao <T> {
    ObservableList<T> getData();
    void setData(T data);
    void delData(T data);
    void upData(T data);
}
