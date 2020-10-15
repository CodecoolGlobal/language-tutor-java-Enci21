package com.codecool.languagetutor.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.codecool.languagetutor.model.Word;
import com.codecool.languagetutor.ui.training.ResultFragment;
import com.codecool.languagetutor.ui.training.TrainingFragment;

import java.util.List;

public class FragmentCollectionAdapter extends FragmentStatePagerAdapter {

    public static final String ID = "id";
    public static final String ENGLISH_WORD = "english_word";
    public static final String TRANSLATION = "translation";
    private int rounds;
    private List<Word> words;

    public FragmentCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == rounds) {
            ResultFragment fragment = new ResultFragment();
            return fragment;

        } else {
            TrainingFragment trainingFragment = TrainingFragment.getInstance();
            Bundle bundle = new Bundle();
            Word word = words.get(position);
            bundle.putLong(ID, word.getId());
            bundle.putString(ENGLISH_WORD, word.getEnWord());
            bundle.putString(TRANSLATION, word.getTranslation());
            trainingFragment.setArguments(bundle);
            return trainingFragment;
        }
    }

    @Override
    public int getCount() {
        return rounds + 1;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

}
