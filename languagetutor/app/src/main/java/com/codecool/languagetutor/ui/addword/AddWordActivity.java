package com.codecool.languagetutor.ui.addword;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.LangTutorApp;
import com.codecool.languagetutor.R;
import com.codecool.languagetutor.model.Word;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddWordActivity extends AppCompatActivity implements AddWordContract.View {

    @Inject
    AddWordContract.Presenter presenter;

    @BindView(R.id.save_button)
    Button saveButton;
    @BindView(R.id.english_word)
    EditText englishWord;
    @BindView(R.id.translation)
    EditText translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        ((LangTutorApp) getApplication()).getComponent().inject(this);
        presenter.onAttach(this);
        ButterKnife.bind(this);

        setClickListener();
    }

    private void setClickListener() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (englishWord.getText().toString().trim().equals("") || translation.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.add_word_empty_field, Toast.LENGTH_SHORT).show();
                } else {
                    Word word = new Word(englishWord.getText().toString().trim(), translation.getText().toString().trim());
                    presenter.insert(word);
                }
            }
        });
    }

    @Override
    public void displayToast() {
        Toast.makeText(this, R.string.word_added_toast_msg, Toast.LENGTH_SHORT).show();
        englishWord.setText("");
        translation.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}