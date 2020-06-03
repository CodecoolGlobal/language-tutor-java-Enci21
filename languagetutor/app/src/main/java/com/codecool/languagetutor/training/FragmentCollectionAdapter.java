package com.codecool.languagetutor.training;

import android.os.Bundle;

import com.codecool.languagetutor.roomDataBase.Word;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentCollectionAdapter extends FragmentStatePagerAdapter {

    private int rounds = 0;
    private List<Word> words;

    public FragmentCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setRounds(int rounds){
        this.rounds = rounds;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        TrainingFragment trainingFragment = new TrainingFragment();
        Bundle bundle = new Bundle();
        Word word = words.get(position);
        position += 1;
        bundle.putString("english_word",word.getEnWord());
        bundle.putString("translation",word.getTranslation());
        trainingFragment.setArguments(bundle);
        return trainingFragment;
    }

    @Override
    public int getCount() {
        return rounds;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
