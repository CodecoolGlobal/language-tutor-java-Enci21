package com.codecool.languagetutor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.addword.AddWordActivity;
import com.codecool.languagetutor.history.HistoryActivity;
import com.codecool.languagetutor.roomDataBase.Word;
import com.codecool.languagetutor.training.TrainingActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_add_word)
    Button addButton;
    @BindView(R.id.button_history)
    Button historyButton;
    @BindView(R.id.button_training)
    Button trainingButton;

    List<Word> incorrectWords;
    public static final String EXTRA_LIST_W = "com.codecool.languagetutor.listW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        incorrectWords = intent.getParcelableArrayListExtra(TrainingActivity.EXTRA_LIST);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
                startActivity(intent);
            }
        });

        trainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
                startActivity(intent);
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                intent.putExtra(EXTRA_LIST_W, (Parcelable) incorrectWords);
                startActivity(intent);
            }
        });
    }

}