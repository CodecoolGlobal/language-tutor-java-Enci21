package com.codecool.languagetutor.ui.history;

import com.codecool.languagetutor.ui.BasePresenter;
import com.codecool.languagetutor.ui.BaseView;
import com.codecool.languagetutor.model.History;

import java.util.List;

public interface HistoryContract {

    interface View extends BaseView<Presenter> {

        void showHistory(List<History> history);

        void showEmptyHistory();
    }

    interface Presenter extends BasePresenter {

        void onAttach(HistoryContract.View view);

        void getAllHistory();
    }
}
