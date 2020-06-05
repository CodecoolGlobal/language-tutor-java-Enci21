package com.codecool.languagetutor.roomDataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addWord(Word word);

    @Query("SELECT * FROM words")
    List<Word> getAllWords();

    @Transaction
    @Query("SELECT * FROM history")
    List<History> getAllHistory();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertHistory(History history);

    @Query("SELECT * FROM history")
    List<HistoryWithWords> getAllHistoryWithWords();

    default List<History> getAllHistories() {
        List<HistoryWithWords> historyWithWords = getAllHistoryWithWords();
        List<History> histories = new ArrayList<History>(historyWithWords.size());
        for (HistoryWithWords history : historyWithWords) {
            history.history.incorrectWords = history.incorrectWords;
            histories.add(history.history);
        }
        return histories;
    }
}
