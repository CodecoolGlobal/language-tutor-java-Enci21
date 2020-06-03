package com.codecool.languagetutor.addword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codecool.languagetutor.R;

public class AddWordActivity extends AppCompatActivity implements AddWordContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
    }

    @Override
    public void setPresenter(AddWordContract.Presenter presenter) {

    }
}