package com.codecool.languagetutor.roomDataBase;

import android.app.Application;

import java.util.List;

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

    public List<Word> getAll() {
        return dao.getAllWords();
    }

    public List<History> getAllHistory() {
        return dao.getAllHistory();
    }

}

