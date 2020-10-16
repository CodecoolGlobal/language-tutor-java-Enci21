package com.codecool.languagetutor;

import com.codecool.languagetutor.model.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordTestData {

    public static List<Word> words = new ArrayList<>(
            Arrays.asList(
                    new Word("table", "asztal"),
                    new Word("apple", "alma"),
                    new Word("pen", "toll"),
                    new Word("dog", "kutya"),
                    new Word("cat", "macska"),
                    new Word("star", "csillag"),
                    new Word("foot", "lab"),
                    new Word("arm", "kar")
            )
    );
}
