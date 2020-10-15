package com.codecool.languagetutor.ui.addword;

import com.codecool.languagetutor.ui.BasePresenter;
import com.codecool.languagetutor.ui.BaseView;
import com.codecool.languagetutor.model.Word;

public interface AddWordContract {

    interface View extends BaseView {
        void displayToast();
    }

    interface Presenter extends BasePresenter<AddWordContract.View> {
        void insert(Word word);
    }
}
