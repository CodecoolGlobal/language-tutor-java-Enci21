package com.codecool.languagetutor.ui.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.codecool.languagetutor.LangTutorApp;
import com.codecool.languagetutor.R;
import com.codecool.languagetutor.databinding.ActivitySpinnerBinding;
import com.codecool.languagetutor.ui.training.TrainingActivity;

import javax.inject.Inject;

public class SpinnerActivity extends AppCompatActivity implements SpinnerContract.View {

    @Inject
    SpinnerContract.Presenter presenter;

    public static final String NUMBER_OF_WORDS = "numberOfWords";
    private ActivitySpinnerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpinnerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ((LangTutorApp) getApplication()).getComponent().inject(this);
        presenter.onAttach(this);
        setUpSpinner();
        setUpClickListener();
    }

    private void setUpSpinner() {
        Integer[] options = {5, 10, 20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
    }

    private void setUpClickListener() {
        binding.spinnerButton.setOnClickListener(v -> {
            int numberOfWords = Integer.parseInt(binding.spinner.getSelectedItem().toString());
            presenter.getWordsCount(numberOfWords);
        });
    }

    @Override
    public void startTrainingActivity(int numberOfWords) {
        Toast.makeText(this, "You will get " + binding.spinner.getSelectedItem().toString() + " questions", Toast.LENGTH_SHORT).show();
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