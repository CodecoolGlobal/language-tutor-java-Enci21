package com.codecool.languagetutor.ui.spinner;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.languagetutor.LangTutorApp;
import com.codecool.languagetutor.R;
import com.codecool.languagetutor.ui.training.TrainingActivity;

import javax.inject.Inject;

public class SpinnerActivity extends AppCompatActivity implements SpinnerContract.View {

    @Inject
    SpinnerContract.Presenter presenter;

    public static final String NUMBER_OF_WORDS = "numberOfWords";

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.spinner_button)
    Button spinnerButton;
    @BindView(R.id.howManyText)
    TextView welcomeTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ButterKnife.bind(this);
        ((LangTutorApp) getApplication()).getComponent().inject(this);
        presenter.onAttach(this);
        setUpSpinner();
        setUpClickListener();
    }

    private void setUpSpinner() {
        Integer[] options = {5, 10, 20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setUpClickListener() {
        spinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfWords = Integer.parseInt(spinner.getSelectedItem().toString());
                presenter.getWordsCount(numberOfWords);
            }
        });
    }

    @Override
    public void startTrainingActivity(int numberOfWords) {
        Toast.makeText(this, "You will get " + spinner.getSelectedItem().toString() + " questions", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SpinnerActivity.this, TrainingActivity.class);
        intent.putExtra(NUMBER_OF_WORDS, numberOfWords);
        startActivity(intent);
        finish();
    }

    @Override
    public void showNotEnoughWordsInDbMsg() {
        Toast.makeText(this, R.string.empty_db_msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}