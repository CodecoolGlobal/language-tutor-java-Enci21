package com.codecool.languagetutor.ui.addword;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.LangTutorApp;
import com.codecool.languagetutor.R;
import com.codecool.languagetutor.databinding.ActivityAddWordBinding;
import com.codecool.languagetutor.model.Word;

import javax.inject.Inject;


public class AddWordActivity extends AppCompatActivity implements AddWordContract.View {

    @Inject
    AddWordContract.Presenter presenter;

    private ActivityAddWordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ((LangTutorApp) getApplication()).getComponent().inject(this);
        presenter.onAttach(this);

        setClickListener();
    }

    private void setClickListener() {
        binding.saveButton.setOnClickListener(v -> {
            if (binding.englishWord.getText().toString().trim().equals("") || binding.translation.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), R.string.add_word_empty_field, Toast.LENGTH_SHORT).show();
            } else {
                Word word = new Word(binding.englishWord.getText().toString().trim(), binding.translation.getText().toString().trim());
                presenter.insert(word);
            }
        });
    }

    @Override
    public void displayToast() {
        Toast.makeText(this, R.string.word_added_toast_msg, Toast.LENGTH_SHORT).show();
        binding.englishWord.setText("");
        binding.translation.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}