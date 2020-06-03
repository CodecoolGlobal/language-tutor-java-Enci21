package com.codecool.languagetutor.history;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codecool.languagetutor.R;

public class HistoryActivity extends AppCompatActivity implements HistoryContract.View {


    private HistoryContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }
}