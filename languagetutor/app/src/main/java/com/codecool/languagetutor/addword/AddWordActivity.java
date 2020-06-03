package com.codecool.languagetutor.addword;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.roomDataBase.Word;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddWordActivity extends AppCompatActivity implements AddWordContract.View{


    private AddWordContract.Presenter presenter;

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

        ButterKnife.bind(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word(englishWord.getText().toString(), translation.getText().toString());
                presenter.insert(word);
            }
        });

        presenter = new AddWordPresenter(this, getApplication());
        presenter.onAttach();
    }

    @Override
    public void setPresenter(AddWordContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void displayToast() {
        Toast.makeText(this, "You added a new word!", Toast.LENGTH_SHORT).show();
    }
}