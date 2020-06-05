package com.codecool.languagetutor.roomDataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.List;

@Entity(tableName = "history")
@TypeConverters(DateConverter.class)
public class History {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "historyId")
    private Long id;

    private Date date;

    private String ratio;

    @Ignore
    List<Word> incorrectWords;

    public List<Word> getIncorrectWords() {
        return incorrectWords;
    }

    public void setIncorrectWords(List<Word> incorrectWords) {
        this.incorrectWords = incorrectWords;
    }

    public History(Date date, String ratio) {
        this.date = date;
        this.ratio = ratio;

    }

    public Date getDate() {
        return date;
    }

    public String getRatio() {
        return ratio;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

}
