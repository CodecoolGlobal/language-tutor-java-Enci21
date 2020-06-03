package com.codecool.languagetutor.addword;

import com.codecool.languagetutor.BasePresenter;
import com.codecool.languagetutor.BaseView;
import com.codecool.languagetutor.roomDataBase.Word;

public interface AddWordContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter{
        void insert(Word word);
    }
}
