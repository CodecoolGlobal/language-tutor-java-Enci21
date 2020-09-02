package com.codecool.languagetutor.ui.training;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.codecool.languagetutor.LangTutorApp;
import com.codecool.languagetutor.adapters.FragmentCollectionAdapter;
import com.codecool.languagetutor.ui.MainActivity;
import com.codecool.languagetutor.R;
import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.model.Word;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainingActivity extends AppCompatActivity implements TrainingContract.View, TrainingFragment.OnResultListener, ResultFragment.ClosingInterface {

    @Inject
    TrainingContract.Presenter presenter;

    private FragmentCollectionAdapter fragmentCollectionAdapter;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    String incorrectWords = "";
    int incorrectWordsAmount = 0;
    int counter = 0;
    int numberOfWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        ((LangTutorApp) getApplication()).getComponent().inject(this);

        presenter.onAttach(this);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            incorrectWords = savedInstanceState.getString("incorrectWords");
            incorrectWordsAmount = savedInstanceState.getInt("incorrectWordsAmount");
            counter = savedInstanceState.getInt("counter");
        }

        numberOfWords = getIntent().getIntExtra("numberOfWords", 5);
        presenter.getWords(numberOfWords);
        progressBar.setMax(numberOfWords);
        progressBar.setProgress(counter);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragments(List<Word> words) {
        fragmentCollectionAdapter = new FragmentCollectionAdapter(getSupportFragmentManager());
        fragmentCollectionAdapter.setRounds(words.size());
        fragmentCollectionAdapter.setWords(words);
        viewPager.setAdapter(fragmentCollectionAdapter);
    }

    @Override
    public void showEmptyDatabaseMessage() {
        Toast.makeText(this, R.string.empty_db_msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(boolean isCorrect, Word word) {
        if (!isCorrect) {
            counter++;
            incorrectWordsAmount++;
            incorrectWords += word.getEnWord() + " " + word.getTranslation() + "\n\n";
            progressBar.setProgress(counter);
        } else {
            counter++;
            progressBar.setProgress(counter);
        }
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    @Override
    public void onClose() {
        String ratio = Integer.toString(numberOfWords) + " / " + Integer.toString(numberOfWords - incorrectWordsAmount);
        Intent intent = new Intent(TrainingActivity.this, MainActivity.class);
        Date date = Calendar.getInstance().getTime();
        History history = new History(date, ratio, incorrectWords);
        presenter.save(history);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("incorrectWords", incorrectWords);
        outState.putInt("incorrectWordsAmount", incorrectWordsAmount);
        outState.putInt("counter", counter);
    }
}