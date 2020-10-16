package com.codecool.languagetutor;

import com.codecool.languagetutor.ui.history.HistoryActivityTest;
import com.codecool.languagetutor.ui.spinner.SpinnerActivityTest;
import com.codecool.languagetutor.ui.splash.SplashActivityTest;
import com.codecool.languagetutor.ui.training.TrainingActivityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
    {HistoryActivityTest.class,
    SpinnerActivityTest.class,
    SplashActivityTest.class,
    TrainingActivityTest.class}
)
public class AppTestSuite {
}
