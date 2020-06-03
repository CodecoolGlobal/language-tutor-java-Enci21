package com.codecool.languagetutor.training;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainingFragment extends Fragment {

    private String translation;

    private TrainingInterface trainingInterface;

    @BindView(R.id.answer)
    EditText answer;
    @BindView(R.id.english_word)
    TextView englishWord;
    @BindView(R.id.check_button)
    Button checkButton;
    @BindView(R.id.correct_answer)
    TextView correctAnswerText;

    View view;

    public TrainingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_training, container, false);
        ButterKnife.bind(this, view);
        String english_word = getArguments().getString("english_word");
        translation = getArguments().getString("translation");
        englishWord.setText(english_word);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
        return view;
    }

    private void checkAnswer() {
        String answerGiven = answer.getText().toString();
        if (answerGiven.toLowerCase().equals(translation)) {
            showCorrect();
        } else {
            showIncorrect();
        }
    }

    private void showIncorrect() {
        correctAnswerText.setText(translation);
        correctAnswerText.setVisibility(View.VISIBLE);
        correctAnswerText.setTextColor(getResources().getColor(R.color.incorrectColor));
        view.setBackgroundColor(getResources().getColor(R.color.incorrectBackground));
    }

    private void showCorrect() {
        correctAnswerText.setText("Correct!");
        correctAnswerText.setVisibility(View.VISIBLE);
        correctAnswerText.setTextColor(getResources().getColor(R.color.correctColor));
        view.setBackgroundColor(getResources().getColor(R.color.correctBackground));
    }

}