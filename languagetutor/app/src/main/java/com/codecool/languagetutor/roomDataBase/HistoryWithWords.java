package com.codecool.languagetutor.roomDataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import java.util.List;

public class HistoryWithWords {

    @Embedded
    History history;

    @Relation(
            parentColumn = "historyId",
            entityColumn = "wordId",
            associateBy = @Junction(HistoryWordCrossRef.class))
    List<Word> incorrectWordsId;

    public HistoryWithWords(History history, List<Word> incorrectWordsId) {
        this.history = history;
        this.incorrectWordsId = incorrectWordsId;
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
