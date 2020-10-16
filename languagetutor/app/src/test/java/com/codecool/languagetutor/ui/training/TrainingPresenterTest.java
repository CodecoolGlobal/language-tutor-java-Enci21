package com.codecool.languagetutor.ui.training;

import com.codecool.languagetutor.WordTestData;
import com.codecool.languagetutor.adapters.FragmentCollectionAdapter;
import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.source.WordRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TrainingPresenterTest {

    @Mock
    WordRepository repository;

    @Mock
    TrainingActivity activity;

    private TrainingPresenter presenter;
    private History history;
    private int rounds;

    @Before
    public void setup() {
        rounds = 5;
        history = new History(new Date(12345L), "4/5", "apple alme \n\n");
        presenter = new TrainingPresenter(repository);
        presenter.onAttach(activity);
        RxJavaPlugins.setIoSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void test_saveHistory() {
        presenter.save(history);
        verify(repository).insertHistory(history);
    }

    @Test
    public void test_getWords() {
        given(repository.getAll()).willReturn(Single.just(WordTestData.words));
        presenter.getWords(rounds);
        then(activity).should().showFragments(anyList());
        then(activity).shouldHaveNoMoreInteractions();
    }
}