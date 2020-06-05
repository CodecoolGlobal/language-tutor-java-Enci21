package com.codecool.languagetutor.history;

import com.codecool.languagetutor.BasePresenter;
import com.codecool.languagetutor.BaseView;
import com.codecool.languagetutor.roomDataBase.History;

import java.util.List;

public interface HistoryContract {

    interface View extends BaseView<Presenter>{

        void showHistory(List<History> history);
    }

    interface Presenter extends BasePresenter{

        //void getAllHistory();
    }
}
