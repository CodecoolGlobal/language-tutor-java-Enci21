package com.codecool.languagetutor.training;

import com.codecool.languagetutor.BasePresenter;
import com.codecool.languagetutor.BaseView;
import com.codecool.languagetutor.roomDataBase.Word;

import java.util.List;

public interface TrainingContract {

    interface View extends BaseView<Presenter> {

    }
    interface Presenter extends BasePresenter{
        List<Word> getWords(int rounds);
    }
}
