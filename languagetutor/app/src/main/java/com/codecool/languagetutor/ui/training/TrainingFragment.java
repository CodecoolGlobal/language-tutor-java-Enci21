package com.codecool.languagetutor.ui.training;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.databinding.FragmentTrainingBinding;
import com.codecool.languagetutor.model.Word;
import com.codecool.languagetutor.util.HideKeyBoard;


public class TrainingFragment extends Fragment {

    public static final String ERROR_MSG = " must implement OnResultListener";
    public static final String ID = "id";
    public static final String ENGLISH_WORD = "english_word";
    public static final String TRANSLATION = "translation";
    private View view;
    private Long wordId;
    private String english_word;
    private String translation;
    private OnResultListener callback;
    private FragmentTrainingBinding binding;

    public interface OnResultListener {
        void onResult(boolean isCorrect, Word word);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (OnResultListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + ERROR_MSG);
        }
    }

    public TrainingFragment() {
    }

    public static TrainingFragment getInstance() {
        TrainingFragment fragment = new TrainingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTrainingBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        wordId = getArguments().getLong(ID);
        english_word = getArguments().getString(ENGLISH_WORD);
        translation = getArguments().getString(TRANSLATION);
        binding.englishWord.setText(english_word);
        setUpClickListener();
        return view;
    }

    private void setUpClickListener() {
        binding.checkButton.setOnClickListener(v -> {
            HideKeyBoard.hideKeyboard(getActivity());
            checkAnswer();
            binding.checkButton.setEnabled(false);
        });
    }

    private void checkAnswer() {
        String answerGiven = binding.answer.getText().toString().trim();
        Word word = new Word(wordId, english_word, translation);
        if (answerGiven.toLowerCase().equals(translation)) {
            showCorrect();
            runDelayedCallback(true, word);
        } else {
            showIncorrect();
            runDelayedCallback(false, word);
        }
    }

    private void runDelayedCallback(boolean isCorrect, Word word) {
        new Handler().postDelayed(() -> callback.onResult(isCorrect, word), 2000);
    }

    private void showIncorrect() {
        binding.correctAnswer.setText(translation);
        binding.correctAnswer.setVisibility(View.VISIBLE);
        binding.correctAnswer.setTextColor(getResources().getColor(R.color.incorrectColor));
        view.setBackgroundColor(getResources().getColor(R.color.incorrectBackground));
    }

    private void showCorrect() {
        binding.correctAnswer.setText(R.string.correct_answer_msg);
        binding.correctAnswer.setVisibility(View.VISIBLE);
        binding.correctAnswer.setTextColor(getResources().getColor(R.color.correctColor));
        view.setBackgroundColor(getResources().getColor(R.color.correctBackground));
    }

    @Override
    public void onDestroyView() {
        callback = null;
        super.onDestroyView();
    }
}