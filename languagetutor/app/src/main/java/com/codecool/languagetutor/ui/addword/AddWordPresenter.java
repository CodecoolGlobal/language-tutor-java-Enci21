package com.codecool.languagetutor.ui.addword;

import com.codecool.languagetutor.model.Word;
import com.codecool.languagetutor.source.WordRepository;

import javax.inject.Inject;

public class AddWordPresenter implements AddWordContract.Presenter {

    private AddWordContract.View view;
    private WordRepository repository;

    @Inject
    public AddWordPresenter( WordRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onAttach(AddWordContract.View view) {
        this.view= view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void insert(Word word) {
        repository.insert(word);
        view.displayToast();
    }
}
