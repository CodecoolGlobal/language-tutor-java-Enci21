package com.codecool.languagetutor.ui.training;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.LangTutorApp;
import com.codecool.languagetutor.adapters.FragmentCollectionAdapter;
import com.codecool.languagetutor.databinding.ActivityTrainingBinding;
import com.codecool.languagetutor.ui.MainActivity;
import com.codecool.languagetutor.R;
import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.model.Word;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;


public class TrainingActivity extends AppCompatActivity implements TrainingContract.View, TrainingFragment.OnResultListener, ResultFragment.ClosingInterface {

    @Inject
    TrainingContract.Presenter presenter;

    private static final String INCORRECTWORDS_TAG = "incorrectWords";
    private static final String INCORRECTWORDSAMOUNT_TAG = "incorrectWordsAmount";
    private static final String COUNTER_TAG = "counter";
    private static final String NUMBEROFWORDS_TAG = "numberOfWords";

    private ActivityTrainingBinding binding;
    private FragmentCollectionAdapter fragmentCollectionAdapter;
    private String incorrectWords = "";
    private int incorrectWordsAmount = 0;
    private int counter = 0;
    private int numberOfWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ((LangTutorApp) getApplication()).getComponent().inject(this);

        presenter.onAttach(this);

        if (savedInstanceState != null) {
            incorrectWords = savedInstanceState.getString(INCORRECTWORDS_TAG);
            incorrectWordsAmount = savedInstanceState.getInt(INCORRECTWORDSAMOUNT_TAG);
            counter = savedInstanceState.getInt(COUNTER_TAG);
        }

        numberOfWords = getIntent().getIntExtra(NUMBEROFWORDS_TAG, 5);
        presenter.getWords(numberOfWords);
        showProgressBar();
    }

    private void showProgressBar() {
        binding.progressBar.setMax(numberOfWords);
        binding.progressBar.setProgress(counter);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragments(List<Word> words) {
        fragmentCollectionAdapter = new FragmentCollectionAdapter(getSupportFragmentManager());
        fragmentCollectionAdapter.setRounds(words.size());
        fragmentCollectionAdapter.setWords(words);
        binding.pager.setAdapter(fragmentCollectionAdapter);
    }

    @Override
    public void onResult(boolean isCorrect, Word word) {
        if (!isCorrect) {
            incorrectWordsAmount++;
            incorrectWords += word.getEnWord() + " " + word.getTranslation() + "\n\n";
        }
        counter++;
        binding.progressBar.setProgress(counter);
        binding.pager.setCurrentItem(binding.pager.getCurrentItem() + 1);
    }

    @Override
    public void saveResult() {
        String ratio = Integer.toString(numberOfWords) + " / " + Integer.toString(numberOfWords - incorrectWordsAmount);
        Date date = Calendar.getInstance().getTime();
        History history = new History(date, ratio, incorrectWords);
        presenter.save(history);
    }

    @Override
    public void onClose() {
        Intent intent = new Intent(TrainingActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INCORRECTWORDS_TAG, incorrectWords);
        outState.putInt(INCORRECTWORDSAMOUNT_TAG, incorrectWordsAmount);
        outState.putInt(COUNTER_TAG, counter);
    }
}