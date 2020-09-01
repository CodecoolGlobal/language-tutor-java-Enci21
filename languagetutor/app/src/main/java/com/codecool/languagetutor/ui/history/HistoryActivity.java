package com.codecool.languagetutor.ui.history;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.codecool.languagetutor.R;
import com.codecool.languagetutor.adapters.HistoryListAdapter;
import com.codecool.languagetutor.model.History;


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
    @BindView(R.id.message)
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        presenter = new HistoryPresenter(this, getApplication());
        adapter = new HistoryListAdapter(allHistory);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyRecyclerView.setAdapter(adapter);
        presenter.getAllHistory();
    }

    @Override
    public void setPresenter(HistoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showHistory(List<History> history) {
        this.allHistory.clear();
        this.allHistory.addAll(history);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyHistory() {
        message.setText(R.string.empty_history_msg);
        message.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}