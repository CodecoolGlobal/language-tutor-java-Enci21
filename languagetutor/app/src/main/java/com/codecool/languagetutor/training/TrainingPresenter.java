package com.codecool.languagetutor.training;

import android.app.Application;
import android.os.AsyncTask;

import com.codecool.languagetutor.roomDataBase.History;
import com.codecool.languagetutor.roomDataBase.Word;
import com.codecool.languagetutor.roomDataBase.WordRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void save(History history) {
        repository.insertHistory(history);
    }

    @Override
    public void getWords(int rounds) {
        new GetAllWordsTask().execute(rounds);
    }

    class GetAllWordsTask extends AsyncTask<Integer, Void, List<Word>> {

        Integer rounds;

        @Override
        protected List<Word> doInBackground(Integer... integers) {
            this.rounds = integers[0];
            return repository.getAll();
        }

        @Override
        protected void onPostExecute(List<Word> words) {
            super.onPostExecute(words);
            if (words.size() == 0){
                view.showEmptyDatabaseMessage();
            } else {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < words.size(); i++) {
                    list.add(i);
                }
                Collections.shuffle(list);
                List<Word> trainingWords = new ArrayList();
                for (int i = 0; i < rounds; i++) {
                    trainingWords.add(words.get(list.get(i)));
                }
                view.showFragments(trainingWords);
            }
        }
    }
}
