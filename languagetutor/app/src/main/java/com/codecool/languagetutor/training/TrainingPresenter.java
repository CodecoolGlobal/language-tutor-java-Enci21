package com.codecool.languagetutor.training;

import android.app.Application;
import android.os.AsyncTask;

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
            Random random = new Random();
            List<Word> trainingWords = new ArrayList();
            for (int i = 0; i < rounds; i++) {
                trainingWords.add(words.get(random.nextInt(words.size())));
            }
            view.showFragments(trainingWords);
        }
    }
}
