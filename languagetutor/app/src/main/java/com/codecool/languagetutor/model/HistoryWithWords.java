package com.codecool.languagetutor.model;

import com.codecool.languagetutor.model.DateConverter;
import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.model.Word;

import androidx.room.Embedded;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import java.util.List;

@TypeConverters(DateConverter.class)
class HistoryWithWords {

    @Embedded
    History history;

    @Relation(
            parentColumn = "historyId",
            entityColumn = "wordId",
            entity = Word.class)
    List<Word> incorrectWords;

    public HistoryWithWords(History history, List<Word> incorrectWords) {
        this.history = history;
        this.incorrectWords = incorrectWords;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }


    public List<Word> getIncorrectWords() {
        return incorrectWords;
    }

    public void setIncorrectWords(List<Word> incorrectWords) {
        this.incorrectWords = incorrectWords;
    }
}
