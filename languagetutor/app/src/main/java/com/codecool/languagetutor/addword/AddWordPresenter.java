package com.codecool.languagetutor.addword;

import android.app.Application;

import com.codecool.languagetutor.roomDataBase.Word;
import com.codecool.languagetutor.roomDataBase.WordRepository;

public class AddWordPresenter implements AddWordContract.Presenter {

    private AddWordContract.View view;
    private WordRepository repository;

    public AddWordPresenter(AddWordContract.View view, Application application) {
        this.view = view;
        this.repository = new WordRepository(application);
    }



    @Override
    public void onAttach() {
        view.setPresenter(this);
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void insert(Word word) {
        repository.insert(word);
    }
}
