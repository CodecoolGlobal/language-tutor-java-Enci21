package com.codecool.languagetutor.history;

import android.view.View;

public class HistoryPresenter implements HistoryContract.Presenter {

    private HistoryContract.View view;

    public HistoryPresenter(HistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void onAttach() {
        view.setPresenter(this);
    }

    @Override
    public void onDetach() {
        this.view = null;
    }
}
