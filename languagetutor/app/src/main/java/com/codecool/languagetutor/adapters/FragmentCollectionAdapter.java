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

    private int rounds;
    private List<Word> words;

    public FragmentCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm);
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
            TrainingFragment trainingFragment = new TrainingFragment();
            Bundle bundle = new Bundle();
            Word word = words.get(position);
            bundle.putLong("id", word.getId());
            bundle.putString("english_word", word.getEnWord());
            bundle.putString("translation", word.getTranslation());
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
