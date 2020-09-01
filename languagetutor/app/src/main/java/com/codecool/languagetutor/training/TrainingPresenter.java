package com.codecool.languagetutor.training;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.codecool.languagetutor.roomDataBase.History;
import com.codecool.languagetutor.roomDataBase.Word;
import com.codecool.languagetutor.roomDataBase.WordRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.MainThread;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrainingPresenter implements TrainingContract.Presenter {

    private TrainingContract.View view;
    private WordRepository repository;
    private static final String ERROR_TAG = "RXJAVA";


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

//        new GetAllWordsTask().execute(rounds);
        repository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Word>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Word> words) {
                        if (words.size() == 0) {
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

                    @Override
                    public void onError(Throwable e) {
                        Log.d(ERROR_TAG, e.getMessage());
                    }
                });

    }

//    class GetAllWordsTask extends AsyncTask<Integer, Void, List<Word>> {
//
//        Integer rounds;
//
//        @Override
//        protected List<Word> doInBackground(Integer... integers) {
//            this.rounds = integers[0];
//            return repository.getAll();
//        }
//
//        @Override
//        protected void onPostExecute(List<Word> words) {
//            super.onPostExecute(words);
//            if (words.size() == 0) {
//                view.showEmptyDatabaseMessage();
//            } else {
//                List<Integer> list = new ArrayList<>();
//                for (int i = 0; i < words.size(); i++) {
//                    list.add(i);
//                }
//                Collections.shuffle(list);
//                List<Word> trainingWords = new ArrayList();
//                for (int i = 0; i < rounds; i++) {
//                    trainingWords.add(words.get(list.get(i)));
//                }
//                view.showFragments(trainingWords);
//            }
//        }
//    }
}
