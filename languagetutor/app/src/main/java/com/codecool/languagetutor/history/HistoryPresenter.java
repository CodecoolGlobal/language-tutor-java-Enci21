package com.codecool.languagetutor.history;

import android.app.Application;
import android.os.AsyncTask;

import com.codecool.languagetutor.roomDataBase.History;
import com.codecool.languagetutor.roomDataBase.WordRepository;

import java.util.List;

public class HistoryPresenter implements HistoryContract.Presenter {

    private HistoryContract.View view;
    private WordRepository repository;

    public HistoryPresenter(HistoryContract.View view, Application app) {
        this.repository = new WordRepository(app);
        this.view = view;
    }

    @Override
    public void onAttach() {
        view.setPresenter(this);
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void getAllHistory() {
        new GetAllHistoryTask().execute();
    }

    class GetAllHistoryTask extends AsyncTask<Void, Void, List<History>> {

        @Override
        protected List<History> doInBackground(Void... voids) {
            return repository.getAllHistory();
        }

        @Override
        protected void onPostExecute(List<History> histories) {
            view.showHistory(histories);
        }
    }
}
