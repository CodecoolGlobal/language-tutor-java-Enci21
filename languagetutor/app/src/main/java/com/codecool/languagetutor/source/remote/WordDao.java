package com.codecool.languagetutor.source.remote;

import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.model.Word;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addWord(Word word);

    @Query("SELECT * FROM words")
    Single<List<Word>> getAllWords();

    @Transaction
    @Query("SELECT * FROM history")
    Single<List<History>> getAllHistory();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertHistory(History history);

    @Query("SELECT Count(*) FROM words")
    Single<Integer> getWordCount();
}
