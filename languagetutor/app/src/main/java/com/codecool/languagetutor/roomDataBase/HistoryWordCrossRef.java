package com.codecool.languagetutor.roomDataBase;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"wordId", "historyId"})
public class HistoryWordCrossRef {

    @NonNull
    private Long wordId;
    @NonNull
    private Long historyId;

    public Long getWordId() {
        return wordId;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }
}
