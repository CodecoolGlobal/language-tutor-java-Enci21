package com.codecool.languagetutor.roomDataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import java.util.List;

@TypeConverters(DateConverter.class)
class HistoryWithWords {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private Long id;

    @Embedded
    History history;

    @Relation(
            parentColumn = "historyId",
            entityColumn = "wordId",
            associateBy = @Junction(HistoryWordCrossRef.class))
    List<Word> incorrectWordsId;

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }


    public List<Word> getIncorrectWordsId() {
        return incorrectWordsId;
    }

    public void setIncorrectWordsId(List<Word> incorrectWordsId) {
        this.incorrectWordsId = incorrectWordsId;
    }
}
