package com.ss.ita.rozetka.test;

import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

public class CatalogModalTest extends TestRunner {

    @Test
    @Description("Verify that catalog opens and closes correctly")
    @TmsLink(value = "LVTAQC630-13")
    public void verifyThatCatalogOpensAndCloses() {
        var header = new HomePage()
                .open()
                .getHeader();

        header.openCatalogModal();

        SoftAssertions softAssertion = new SoftAssertions();
        softAssertion
                .assertThat(header.isCatalogModalVisible())
                .as("Catalog modal should be visible")
                .isTrue();

        header.closeCatalogModal();
        softAssertion
                .assertThat(header.isCatalogModalVisible())
                .as("Catalog modal shouldn't be visible")
                .isFalse();
        softAssertion.assertAll();
    }
}
