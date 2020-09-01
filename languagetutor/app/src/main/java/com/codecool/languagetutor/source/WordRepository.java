package com.codecool.languagetutor.source;

import android.app.Application;

import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.model.Word;
import com.codecool.languagetutor.source.remote.WordDao;
import com.codecool.languagetutor.source.remote.WordDatabase;

import java.util.List;

import io.reactivex.Single;

public class WordRepository {

    private WordDao dao;

    public WordRepository(Application application) {
        WordDatabase database = WordDatabase.getDatabase(application);
        this.dao = database.wordDao();
    }

    public void insert(Word word) {
        WordDatabase.databaseWriteExecutor.execute(() -> {
            dao.addWord(word);
        });
    }

    public void insertHistory(History history) {
        WordDatabase.databaseWriteExecutor.execute(() -> {
            dao.insertHistory(history);
        });
    }

    public Single<List<Word>> getAll() {
        return dao.getAllWords();
    }

    public Single<List<History>> getAllHistory() {
        return dao.getAllHistory();
    }

}

