package com.codecool.languagetutor;

import com.codecool.languagetutor.ui.addword.AddWordPresenterTest;
import com.codecool.languagetutor.ui.history.HistoryPresenterTest;
import com.codecool.languagetutor.ui.spinner.SpinnerPresenterTest;
import com.codecool.languagetutor.ui.training.TrainingPresenterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                HistoryPresenterTest.class,
                AddWordPresenterTest.class,
                SpinnerPresenterTest.class,
                TrainingPresenterTest.class
        }
)
public class UnitTestAppSuite {
}
