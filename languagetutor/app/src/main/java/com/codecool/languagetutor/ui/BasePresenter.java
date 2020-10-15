package com.codecool.languagetutor.ui;

public interface BasePresenter<T> {
    void onAttach(T view);
    void onDetach();
}
