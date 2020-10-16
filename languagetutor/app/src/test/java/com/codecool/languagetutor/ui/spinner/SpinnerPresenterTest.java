package com.codecool.languagetutor.ui.spinner;

import android.widget.Spinner;

import com.codecool.languagetutor.HistoryTestData;
import com.codecool.languagetutor.source.WordRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SpinnerPresenterTest {

    @Mock
    WordRepository repository;

    @Mock
    SpinnerActivity activity;

    SpinnerPresenter presenter;
    int numberOfWords = 5;
    Integer wordCountLarger = 10;
    int wordCountSmaller = 3;

    @Before
    public void setup() {
        presenter = new SpinnerPresenter(repository);
        presenter.onAttach(activity);
        RxJavaPlugins.setIoSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void test_getWordsCount_Larger() {
        given(repository.getWordsCount()).willReturn(Single.just(wordCountLarger));
        presenter.getWordsCount(numberOfWords);
        verify(activity).startTrainingActivity(numberOfWords);
    }

    @Test
    public void test_getWordsCount_Smaller() {
        given(repository.getWordsCount()).willReturn(Single.just(wordCountSmaller));
        presenter.getWordsCount(numberOfWords);
        verify(activity).showNotEnoughWordsInDbMsg();
    }
}