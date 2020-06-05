package com.codecool.languagetutor.history;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.roomDataBase.History;
import com.codecool.languagetutor.roomDataBase.HistoryWithWords;
import com.codecool.languagetutor.roomDataBase.HistoryWordCrossRef;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryActivity extends AppCompatActivity implements HistoryContract.View {


    private HistoryContract.Presenter presenter;
    private List<HistoryWithWords> allHistory = new ArrayList<>();
    HistoryListAdapter adapter;

    @BindView(R.id.historyRecyclerView)
    RecyclerView historyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        presenter = new HistoryPresenter(this, getApplication());
        presenter.getAllHistory();
        adapter = new HistoryListAdapter(allHistory);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showHistory(List<HistoryWithWords> history) {
        this.allHistory.addAll(history);
        adapter.notifyDataSetChanged();
    }
}