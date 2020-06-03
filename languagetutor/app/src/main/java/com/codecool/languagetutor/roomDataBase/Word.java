package com.codecool.languagetutor.roomDataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    @NonNull
    Long id;

    @NonNull
    String enWord;

    @NonNull
    String translation;


    public Word(@NonNull String enWord, @NonNull String translation) {
        this.enWord = enWord;
        this.translation = translation;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    @NonNull
    public String getEnWord() {
        return enWord;
    }

    @NonNull
    public String getTranslation() {
        return translation;
    }

    public void setEnWord(@NonNull String enWord) {
        this.enWord = enWord;
    }

    public void setTranslation(@NonNull String translation) {
        this.translation = translation;
    }
}
