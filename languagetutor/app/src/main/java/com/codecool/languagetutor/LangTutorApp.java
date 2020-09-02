package com.codecool.languagetutor;

import android.app.Application;

import com.codecool.languagetutor.di.AppComponent;
import com.codecool.languagetutor.di.AppModule;
import com.codecool.languagetutor.di.DaggerAppComponent;

public class LangTutorApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getComponent() {
        return appComponent;
    }

}
