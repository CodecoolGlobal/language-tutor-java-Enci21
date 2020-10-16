package com.codecool.languagetutor;

import com.codecool.languagetutor.model.History;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HistoryTestData {

    public static List<History> provideHistory = new ArrayList<>(
                Arrays.asList(
                        new History(new Date(123344L), "4/5", "apple alma"),
                        new History(new Date(123344L), "3/5", "apple alma \n\n pen toll \n\n dog kutya \n\n "),
                        new History(new Date(123344L), "4/5", "dog kutya"),
                        new History(new Date(123344L), "2/5", "apple alma"),
                        new History(new Date(123344L), "1/5", "apple alma \n\n pen toll \n\n dog kutya \n\n cat macska \n\n ")
                )
    );

}
