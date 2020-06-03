package com.codecool.languagetutor.training;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.codecool.languagetutor.R;

public class TrainingActivity extends AppCompatActivity implements TrainingContract.View {


    private TrainingContract.Presenter presenter;
    @BindView(R.id.spinner)
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        presenter = new TrainingPresenter(this, getApplication());
        presenter.onAttach();

        ButterKnife.bind(this);

        Integer[] options = {5, 10, 20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, R.layout.activity_training, options);
        spinner.setAdapter(adapter);

    }

    @Override
    public void setPresenter(TrainingContract.Presenter presenter) {
        this.presenter= presenter;
    }
}