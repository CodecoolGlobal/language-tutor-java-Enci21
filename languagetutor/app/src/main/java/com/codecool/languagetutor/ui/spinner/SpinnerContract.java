package com.codecool.languagetutor.ui.spinner;

import com.codecool.languagetutor.ui.BasePresenter;
import com.codecool.languagetutor.ui.BaseView;

public interface SpinnerContract {

    interface View extends BaseView{
        void startTrainingActivity(int numberOfWords);

        void showNotEnoughWordsInDbMsg();
    }

    interface Presenter extends BasePresenter<SpinnerContract.View> {
        void getWordsCount(int numberOfWords);
    }
}
