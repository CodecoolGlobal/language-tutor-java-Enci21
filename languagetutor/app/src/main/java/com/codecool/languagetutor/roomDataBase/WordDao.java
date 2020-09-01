package com.codecool.languagetutor.roomDataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addWord(Word word);

    @Query("SELECT * FROM words")
    Single<List<Word>> getAllWords();

    @Transaction
    @Query("SELECT * FROM history")
    List<History> getAllHistory();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertHistory(History history);

}
