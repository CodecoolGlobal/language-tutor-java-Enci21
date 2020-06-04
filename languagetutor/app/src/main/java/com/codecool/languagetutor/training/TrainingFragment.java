package com.codecool.languagetutor.training;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codecool.languagetutor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainingFragment extends Fragment {

    private String translation;

    @BindView(R.id.answer)
    EditText answer;
    @BindView(R.id.english_word)
    TextView englishWord;
    @BindView(R.id.check_button)
    Button checkButton;
    @BindView(R.id.correct_answer)
    TextView correctAnswerText;

    View view;

    private OnResultListener callback;
    private  int position;

    public interface OnResultListener{
        void onResult();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (OnResultListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnResultListener");
        }
    }

    public TrainingFragment() {
    }

    public static TrainingFragment getInstance(int position) {
        TrainingFragment fragment = new TrainingFragment();
        return fragment;
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
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    callback.onResult();
                }
            }, 2000);

//            ezzel is lehet a callback helyett
//            ((TrainingActivity) getActivity()).goToNextPage();
        } else {
            showIncorrect();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    callback.onResult();
                }
            }, 2000);
//        ((TrainingActivity) getActivity()).goToNextPage();
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