package com.codecool.languagetutor.ui.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.codecool.languagetutor.databinding.ActivitySpinnerBinding;
import com.codecool.languagetutor.ui.training.TrainingActivity;

public class SpinnerActivity extends AppCompatActivity {

    public static final String NUMBER_OF_WORDS = "numberOfWords";
    private ActivitySpinnerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpinnerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
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
        binding.spinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfWords = Integer.parseInt(binding.spinner.getSelectedItem().toString());
                Toast.makeText(v.getContext(), "You will get " + binding.spinner.getSelectedItem().toString() + " questions", Toast.LENGTH_SHORT).show();
                startTraining(numberOfWords);
            }
        });
    }

    private void startTraining(int numberOfWords) {
        Intent intent = new Intent(SpinnerActivity.this, TrainingActivity.class);
        intent.putExtra(NUMBER_OF_WORDS, numberOfWords);
        startActivity(intent);
        finish();
    }
}