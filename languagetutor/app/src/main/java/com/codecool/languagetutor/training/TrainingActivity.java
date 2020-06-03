package com.codecool.languagetutor.training;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.roomDataBase.Word;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainingActivity extends AppCompatActivity implements TrainingContract.View, TrainingFragment.OnResultListener {

    private TrainingContract.Presenter presenter;
    private FragmentCollectionAdapter fragmentCollectionAdapter;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.spinner_button)
    Button spinnerButton;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        presenter = new TrainingPresenter(this, getApplication());
        presenter.onAttach();

        ButterKnife.bind(this);

        Integer[] options = {5, 10, 20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfWords = Integer.parseInt(spinner.getSelectedItem().toString());
                Toast.makeText(v.getContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                presenter.getWords(numberOfWords);
            }
        });
    }

    @Override
    public void setPresenter(TrainingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showFragments(List<Word> words) {
        fragmentCollectionAdapter = new FragmentCollectionAdapter(getSupportFragmentManager());
        fragmentCollectionAdapter.setRounds(words.size());
        fragmentCollectionAdapter.setWords(words);
        viewPager.setAdapter(fragmentCollectionAdapter);
    }

//    public void goToNextPage(){
//        viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
//    }

    @Override
    public void onResult() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
    }
}