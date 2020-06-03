package com.codecool.languagetutor.training;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView correctAnswer;

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
        View view = inflater.inflate(R.layout.fragment_training, container, false);
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
        if (answerGiven.toLowerCase().equals(translation)){
            showCorrect();
            callback.onResult();
//            ezzel is lehet a callback helyett
//            ((TrainingActivity) getActivity()).goToNextPage();
        } else {
            showIncorrect();
            callback.onResult();
//        ((TrainingActivity) getActivity()).goToNextPage();
        }
    }

    private void showIncorrect() {
        correctAnswer.setText(translation);
        correctAnswer.setVisibility(View.VISIBLE);
    }

    private void showCorrect() {
        Toast.makeText(getContext(), "Correct!", Toast.LENGTH_SHORT).show();
    }


}