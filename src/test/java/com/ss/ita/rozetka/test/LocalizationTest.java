package com.ss.ita.rozetka.test;

import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.ss.ita.rozetka.pageobject.utils.Language.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalizationTest extends TestRunner {

    @Test
    @TmsLink(value = "LVTAQC630-9")
    @Description("Verify that user can change language")
    public void verifyUserCanChangeLanguage() {
        var homePage = new HomePage().open();
        var header = homePage.getHeader();

        header.changeLanguage(UA);

        assertThat(homePage.getGreetingsText())
                .as("Language should be changed")
                .contains("Ласкаво просимо!");

        header.changeLanguage(RU);

        assertThat(homePage.getGreetingsText())
                .as("Language should be changed")
                .contains("Добро пожаловать!");
    }
}
