package com.codecool.languagetutor.ui.addword;

import com.codecool.languagetutor.model.Word;
import com.codecool.languagetutor.source.WordRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddWordPresenterTest {

    @Mock
    WordRepository repository;

    @Mock
    AddWordActivity activity;

    AddWordPresenter presenter;

    Word word;

    @Before
    public void setup() {
        word = new Word("pasta", "teszta");
        presenter = new AddWordPresenter(repository);
        presenter.onAttach(activity);
        RxJavaPlugins.setIoSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Test
    public void test_insertWord() {
        presenter.insert(word);
        verify(repository).insert(word);
        verify(activity).displayToast();
    }
}