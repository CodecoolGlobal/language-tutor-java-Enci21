package com.codecool.languagetutor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.databinding.ActivityMainBinding;
import com.codecool.languagetutor.ui.addword.AddWordActivity;
import com.codecool.languagetutor.ui.history.HistoryActivity;
import com.codecool.languagetutor.ui.reminder.ReminderActivity;
import com.codecool.languagetutor.ui.spinner.SpinnerActivity;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setContentView(view);
        setUpMenuClickListeners();
    }

    private void setUpMenuClickListeners() {
        binding.buttonAddWord.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
            startActivity(intent);
        });

        binding.buttonTraining.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
            startActivity(intent);
        });

        binding.buttonHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reminder:
                startActivity(new Intent(this, ReminderActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}