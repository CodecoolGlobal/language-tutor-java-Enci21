package com.codecool.languagetutor.training;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codecool.languagetutor.R;

public class TrainingActivity extends AppCompatActivity implements TrainingContract.View {


    private TrainingContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        presenter = new TrainingPresenter(this, getApplication());
        presenter.onAttach();
    }

    @Override
    public void setPresenter(TrainingContract.Presenter presenter) {
        this.presenter= presenter;
    }
}