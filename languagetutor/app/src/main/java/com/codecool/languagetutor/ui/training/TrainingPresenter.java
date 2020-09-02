package com.codecool.languagetutor.ui.training;

import android.util.Log;

import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.model.Word;
import com.codecool.languagetutor.source.WordRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrainingPresenter implements TrainingContract.Presenter {

    private TrainingContract.View view;
    private WordRepository repository;

    @Inject
    public TrainingPresenter( WordRepository repository) {
        this.repository = repository;
    }

    private static final String ERROR_TAG = "RXJAVA";

    @Override
    public void onAttach(TrainingContract.View view) {
        this.view = view;
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
        repository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Word>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Word> words) {
                        if (words.size() == 0 || words.size() < rounds) {
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
}
