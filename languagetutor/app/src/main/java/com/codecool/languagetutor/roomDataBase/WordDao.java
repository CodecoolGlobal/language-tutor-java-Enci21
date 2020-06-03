package com.codecool.languagetutor.roomDataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addWord(Word word);

    @Query("SELECT * FROM words")
    List<Word> getAllWords();
}
