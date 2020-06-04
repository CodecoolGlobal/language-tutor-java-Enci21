package com.codecool.languagetutor.history;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.roomDataBase.History;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryActivity extends AppCompatActivity implements HistoryContract.View {


    private HistoryContract.Presenter presenter;

    @BindView(R.id.textView2)
    TextView anything;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        presenter = new HistoryPresenter(this, getApplication());
        presenter.getAllHistory();
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showHistory(List<History> history) {
        anything.setText(history.get(0).getRatio());
    }
}