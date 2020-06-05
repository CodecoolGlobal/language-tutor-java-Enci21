package com.codecool.languagetutor.roomDataBase;

import android.app.Application;

import java.util.Date;
import java.util.List;

public class WordRepository {

    private WordDao dao;
    private long lastId;

    public WordRepository(Application application) {
        WordDatabase database = WordDatabase.getDatabase(application);
        this.dao = database.wordDao();
    }

    public void insert(Word word) {
        WordDatabase.databaseWriteExecutor.execute(() -> {
            dao.addWord(word);
        });
    }

    public long insertHistory(History history) {

        WordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                lastId = dao.insertHistory(history);
            }
        });
        System.out.println("-----------id" + lastId);
        return lastId;
    }

    public List<Word> getAll() {
        return dao.getAllWords();
    }

    public List<HistoryWithWords> getAllHistory() {
        return dao.getAllHistory();
    }


    public void insertHistoryWithWordsCrossRef(History history, List<Word> words) {
        WordDatabase.databaseWriteExecutor.execute(()-> {
            for (Word w: words){
                dao.insertHWCrosRef(new HistoryWordCrossRef(history.getId(), w.getId()));
            }
        });
    }
}

