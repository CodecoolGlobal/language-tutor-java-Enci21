package com.codecool.languagetutor.training;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentCollectionAdapter extends FragmentStatePagerAdapter {

    private int rounds = 0;

    public FragmentCollectionAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setRounds(int rounds){
        this.rounds = rounds;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return rounds;
    }
}
