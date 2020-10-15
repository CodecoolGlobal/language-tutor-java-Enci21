package com.codecool.languagetutor.source.remote;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.codecool.languagetutor.model.History;
import com.codecool.languagetutor.model.HistoryWordCrossRef;
import com.codecool.languagetutor.model.Word;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Word.class, History.class, HistoryWordCrossRef.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    public static final String LANGUAGE_TUTOR__DB = "language_tutor__db2";

    public abstract WordDao wordDao();

    private static volatile WordDatabase INSTANCE;
    private static final int NUM_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_OF_THREADS);

    public static WordDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, LANGUAGE_TUTOR__DB)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
