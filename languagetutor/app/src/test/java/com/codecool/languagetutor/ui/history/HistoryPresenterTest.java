package com.codecool.languagetutor.ui.history;

import com.codecool.languagetutor.HistoryTestData;
import com.codecool.languagetutor.source.WordRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HistoryPresenterTest {

    @Mock
    WordRepository repository;

    HistoryPresenter presenter;
    HistoryActivity activity = mock(HistoryActivity.class);

    @Before
    public void setup() {
        presenter = new HistoryPresenter(repository);
        presenter.onAttach(activity);
        RxJavaPlugins.setIoSchedulerHandler( schedulerCallable -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler( schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void test_getAllHistories() {
        given(repository.getAllHistory()).willReturn(Single.just(HistoryTestData.provideHistory));
        presenter.getAllHistory();
        verify(activity).showHistory(HistoryTestData.provideHistory);
    }

    @Test
    public void test_emptyHistories() {
        given(repository.getAllHistory()).willReturn(Single.just(Collections.emptyList()));
        presenter.getAllHistory();
        verify(activity).showEmptyHistory();
    }
}