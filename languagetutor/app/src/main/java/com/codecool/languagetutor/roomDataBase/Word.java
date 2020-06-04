package com.codecool.languagetutor.roomDataBase;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class Word implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wordId")
    @NonNull
    Long id;

    @NonNull
    String enWord;

    @NonNull
    String translation;


    public Word(Parcel p) {
        this.id = p.readLong();
        this.enWord = p.readString();
        this.translation = p.readString();

    }

    public Word(@NonNull String enWord, @NonNull String translation) {
        this.enWord = enWord;
        this.translation = translation;
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel source) {
            return new Word(source);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public Word(Long wordId, String english_word, String translation) {
        this.id = wordId;
        this.enWord = english_word;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(this.enWord);
        dest.writeString(this.translation);
    }
}
