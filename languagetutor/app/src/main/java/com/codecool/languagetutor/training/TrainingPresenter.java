package com.codecool.languagetutor.training;

import android.app.Application;

import com.codecool.languagetutor.roomDataBase.WordRepository;

public class TrainingPresenter implements TrainingContract.Presenter {

    private TrainingContract.View view;
    private WordRepository repository;

    public TrainingPresenter(TrainingContract.View view, Application application) {
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
}
