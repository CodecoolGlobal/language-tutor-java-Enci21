package com.codecool.languagetutor.di;

import android.app.Application;

import com.codecool.languagetutor.source.WordRepository;
import com.codecool.languagetutor.ui.addword.AddWordContract;
import com.codecool.languagetutor.ui.addword.AddWordPresenter;
import com.codecool.languagetutor.ui.history.HistoryContract;
import com.codecool.languagetutor.ui.history.HistoryPresenter;
import com.codecool.languagetutor.ui.training.TrainingContract;
import com.codecool.languagetutor.ui.training.TrainingPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    public WordRepository getRepository() {
        return new WordRepository(provideApplication());
    }

    @Provides
    public TrainingContract.Presenter getTrainingPresenter() {
        return new TrainingPresenter(getRepository());
    }

    @Provides
    public HistoryContract.Presenter getHistoryPresenter() {
        return new HistoryPresenter(getRepository());
    }

    @Provides
    public AddWordContract.Presenter getAddWordPresenter() {
        return new AddWordPresenter(getRepository());
    }
}
