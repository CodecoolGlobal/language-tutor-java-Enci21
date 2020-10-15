package com.codecool.languagetutor.ui.spinner;

import android.util.Log;

import com.codecool.languagetutor.source.WordRepository;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SpinnerPresenter implements SpinnerContract.Presenter {

    private static final String ERROR_TAG = "Spinner RXJAVA";
    private SpinnerContract.View view;
    private WordRepository repository;

    @Inject
    public SpinnerPresenter(WordRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getWordsCount(int numberOfWords) {
        repository.getWordsCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(Integer count) {
                        checkIfEnoughWords(count, numberOfWords);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(ERROR_TAG, e.getMessage());
                    }
                });
    }

    private void checkIfEnoughWords(Integer count, int numberOfWords) {
        if (count < numberOfWords) {
            view.showNotEnoughWordsInDbMsg();
        } else {
            view.startTrainingActivity(numberOfWords);
        }
    }

    @Override
    public void onAttach(SpinnerContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }
}
