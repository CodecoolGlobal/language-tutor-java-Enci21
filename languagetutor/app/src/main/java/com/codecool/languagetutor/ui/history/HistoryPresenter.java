package com.codecool.languagetutor.ui.history;

import android.app.Application;
import android.util.Log;

import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.source.WordRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryPresenter implements HistoryContract.Presenter {

    private HistoryContract.View view;
    private WordRepository repository;
    private static final String ERROR_TAG = "history_rxjava";

    @Inject
    public HistoryPresenter(WordRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onAttach(HistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void getAllHistory() {

        repository.getAllHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<History>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<History> histories) {
                        if (!histories.isEmpty()){
                            view.showHistory(histories);
                        } else {
                            view.showEmptyHistory();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(ERROR_TAG, e.getMessage());
                    }
                });
    }

}
