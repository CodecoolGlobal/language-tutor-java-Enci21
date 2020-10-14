package com.codecool.languagetutor.ui.history;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.codecool.languagetutor.LangTutorApp;
import com.codecool.languagetutor.R;
import com.codecool.languagetutor.adapters.HistoryListAdapter;
import com.codecool.languagetutor.databinding.ActivityHistoryBinding;
import com.codecool.languagetutor.model.History;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HistoryActivity extends AppCompatActivity implements HistoryContract.View {

    @Inject
    HistoryContract.Presenter presenter;

    private List<History> allHistory = new ArrayList<>();
    private HistoryListAdapter adapter;
    private ActivityHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ((LangTutorApp) getApplication()).getComponent().injectHistory(this);
        presenter.onAttach(this);
        presenter.getAllHistory();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        adapter = new HistoryListAdapter(allHistory);
        binding.historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.historyRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showHistory(List<History> history) {
        this.allHistory.clear();
        this.allHistory.addAll(history);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyHistory() {
        binding.message.setText(R.string.empty_history_msg);
        binding.message.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}