package com.codecool.languagetutor.di;

import com.codecool.languagetutor.ui.addword.AddWordActivity;
import com.codecool.languagetutor.ui.history.HistoryActivity;
import com.codecool.languagetutor.ui.spinner.SpinnerActivity;
import com.codecool.languagetutor.ui.training.TrainingActivity;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(TrainingActivity activity);

    void injectHistory(HistoryActivity activity);

    void inject(AddWordActivity activity);

    void inject(SpinnerActivity activity);
}
