package com.codecool.languagetutor.training;

import android.app.Application;

import com.codecool.languagetutor.roomDataBase.Word;
import com.codecool.languagetutor.roomDataBase.WordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    @Override
    public List<Word> getWords(int rounds) {
        List<Word> allWords = repository.getAll();
        Random random = new Random();
        List<Word> trainingWords = new ArrayList();
        for (int i=0; i< rounds; i++){
            trainingWords.add(allWords.get(random.nextInt(allWords.size())));
        }
        return trainingWords;
    }
}
