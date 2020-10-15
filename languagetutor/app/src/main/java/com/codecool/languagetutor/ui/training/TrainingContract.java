package com.codecool.languagetutor.ui.training;

import com.codecool.languagetutor.ui.BasePresenter;
import com.codecool.languagetutor.ui.BaseView;
import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.model.Word;

import java.util.List;

public interface TrainingContract {

    interface View extends BaseView {

        void showFragments(List<Word> words);
    }

    interface Presenter extends BasePresenter<TrainingContract.View> {

        void save(History h);

        void getWords(int rounds);
    }
}
