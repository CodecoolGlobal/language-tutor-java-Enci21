package com.codecool.languagetutor.source.remote;

import android.content.Context;

import com.codecool.languagetutor.model.Word;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import io.reactivex.functions.Predicate;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class WordDatabaseTest {

    private WordDao wordDao;
    private WordDatabase db;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @Before
    public void createDb() throws Exception {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(targetContext, WordDatabase.class)
                .allowMainThreadQueries()
                .build();
        wordDao = db.wordDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void test_InsertWord() throws IOException{
        Word word = new Word("test", "test");
        wordDao.addWord(word);
        wordDao.getAllWords()
                .test()
                .assertValue(new Predicate<List<Word>>() {
                    @Override
                    public boolean test(List<Word> words) throws Exception {
                        return words.size() == 1;
                    }
                })
                .assertValue(new Predicate<List<Word>>() {
                    @Override
                    public boolean test(List<Word> words) throws Exception {
                        return word.enWord.equals(words.get(0).enWord) &&
                                word.translation.equals(words.get(0).translation);
                    }
                });
    }
}