package com.codecool.languagetutor.roomDataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addWord(Word word);

    @Query("SELECT * FROM words")
    List<Word> getAllWords();

    @Transaction
    @Query("SELECT * FROM history")
    List<HistoryWithWords> getAllHistory();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertHistory(History history);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertHWCrosRef(HistoryWordCrossRef historyWordCrossRef);
}
