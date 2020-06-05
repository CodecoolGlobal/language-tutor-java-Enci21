package com.codecool.languagetutor.history;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.languagetutor.MainActivity;
import com.codecool.languagetutor.R;
import com.codecool.languagetutor.roomDataBase.History;
import com.codecool.languagetutor.roomDataBase.Word;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryActivity extends AppCompatActivity implements HistoryContract.View {


    private HistoryContract.Presenter presenter;
    private List<History> allHistory = new ArrayList<>();
    HistoryListAdapter adapter;

    @BindView(R.id.historyRecyclerView)
    RecyclerView historyRecyclerView;

    List<Word> incorrectW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        incorrectW = intent.getParcelableArrayListExtra(MainActivity.EXTRA_LIST_W);
        presenter = new HistoryPresenter(this, getApplication());
        //presenter.getAllHistory();
        adapter = new HistoryListAdapter(allHistory);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showHistory(List<History> history) {
        this.allHistory.addAll(history);
        adapter.notifyDataSetChanged();
    }
}