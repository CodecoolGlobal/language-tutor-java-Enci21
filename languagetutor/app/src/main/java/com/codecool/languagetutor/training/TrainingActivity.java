package com.codecool.languagetutor.training;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.codecool.languagetutor.MainActivity;
import com.codecool.languagetutor.R;
import com.codecool.languagetutor.roomDataBase.History;
import com.codecool.languagetutor.roomDataBase.Word;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainingActivity extends AppCompatActivity implements TrainingContract.View, TrainingFragment.OnResultListener, ResultFragment.ClosingInterface {

    private TrainingContract.Presenter presenter;
    private FragmentCollectionAdapter fragmentCollectionAdapter;

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.spinner_button)
    Button spinnerButton;

    @BindView(R.id.howManyText)
    TextView welcomeTraining;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    List<Word> incorrectWords = new ArrayList<>();
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        presenter = new TrainingPresenter(this, getApplication());
        presenter.onAttach();
        ButterKnife.bind(this);

        Integer[] options = {5, 10, 20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfWords = Integer.parseInt(spinner.getSelectedItem().toString());
                Toast.makeText(v.getContext(), "You will get " + spinner.getSelectedItem().toString() + " questions", Toast.LENGTH_SHORT).show();
                presenter.getWords(numberOfWords);
                progressBar.setMax(numberOfWords);
                progressBar.setProgress(counter);
                progressBar.setVisibility(View.VISIBLE);
                welcomeTraining.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                spinnerButton.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void setPresenter(TrainingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showFragments(List<Word> words) {
        fragmentCollectionAdapter = new FragmentCollectionAdapter(getSupportFragmentManager());
        fragmentCollectionAdapter.setRounds(words.size());
        fragmentCollectionAdapter.setWords(words);
        viewPager.setAdapter(fragmentCollectionAdapter);

    }

//    public void goToNextPage(){
//        viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
//    }

    @Override
    public void onResult(boolean isCorrect, Word word) {
        if (!isCorrect) {
            counter++;
            incorrectWords.add(word);
            progressBar.setProgress(counter);
        } else {
            counter++;
            progressBar.setProgress(counter);
        }
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    @Override
    public void onClose() {
        Intent intent = new Intent(TrainingActivity.this, MainActivity.class);
        Date c = Calendar.getInstance().getTime();
        History history = new History(c, "loasz");
        presenter.save(history, incorrectWords);
        startActivity(intent);
    }
}