package com.ss.ita.rozetka.test;


import com.google.common.collect.Lists;
import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.pages.ProductTypePage;
import com.ss.ita.rozetka.pageobject.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Arrays;


import static com.ss.ita.rozetka.pageobject.utils.Language.UA;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchFunctionalityTest extends TestRunner {

    @Test
    @Description("Verify that user can do search with Xiaomi products")
    @TmsLink(value = "LVTAQC630-10")
    public void verifyXiaomiWillBeInSearchResult() {
        var searchProduct = "Xiaomi";

        var productTypePage = new HomePage()
                .open()
                .getHeader()
                .doSearch(searchProduct);

        assertThat(productTypePage.getProductTitle(1))
                .as("Product should have Xiaomi in title")
                .contains(searchProduct);
    }

    @Test
    @TmsLink(value = "LVTAQC630-4")
    @Description(value = "Verify that searched product was added to search history")
    public void verifySearchHistoryText() {
        var header = new HomePage()
                .open()
                .getHeader();
        var searchTerm = "DELL";
        var searchResultPage = header.doSearch(searchTerm);
        assertThat(searchResultPage.isOpened())
                .as("Product type page should be opened")
                .isTrue();
        var homePage = header.openHomePage();
        assertThat(homePage.isOpened())
                .as("Home page should be opened")
                .isTrue();
        int numberSearchTerm = 1;
        var actualSearchTerm = header
                .setSearchInputInFocus()
                .getTextFromSearchHistory(numberSearchTerm);
        assertThat(actualSearchTerm)
                .as("Last search term and first text in search history should be equals")
                .isEqualTo(searchTerm);
    }

    @Test
    @TmsLink(value = "LVTAQC630-61")
    @Description(value = "Verify that items in the search history in right order and search from search history works correct")
    public void verifySearchFromSearchHistory() {
        var homePage = new HomePage()
                .open()
                .getHeader()
                .changeLanguage(UA)
                .openHomePage();
        var searchTermsList = Arrays.asList("Dell", "HP", "IPhone", "Stihl");
        var softAssert = new SoftAssertions();
        ProductTypePage searchResultPage;
        var header = homePage.getHeader();
        for (var searchTerm : searchTermsList) {
            searchResultPage = header.doSearch(searchTerm);
            assertThat(searchResultPage.isOpened())
                    .as("Search result page should be displayed")
                    .isTrue();
            homePage = header.openHomePage();
            assertThat(homePage.isOpened())
                    .as("Home page should be opened")
                    .isTrue();
        }
        var searchHistoryTermsList = header.getSearchHistoryTermsList();
        assertThat(searchTermsList)
                .as("search terms count should be equals to search history terms count")
                .hasSameSizeAs(searchHistoryTermsList);
        assertThat(searchHistoryTermsList)
                .as("Search terms list should have same elements as search history terms list")
                .containsExactlyInAnyOrderElementsOf(searchTermsList);
        assertThat(Lists.reverse(searchHistoryTermsList))
                .as("Search history terms list should be reverse ordered to search terms list")
                .isEqualTo(searchTermsList);
        int productCount;
        for (int i = 1; i <= searchHistoryTermsList.size(); i++) {
            var searchTerm = header.getTextFromSearchHistory(i);
            searchResultPage = header.openItemFromSearchHistory(i);
            assertThat(searchResultPage.isOpened())
                    .as("Search result page should be displayed")
                    .isTrue();
            productCount = searchResultPage.getProductsCount();
            for (int j = 1; j <= productCount; j++) {
                var productTitle = searchResultPage
                        .getProduct(j)
                        .getTitle()
                        .toUpperCase();
                softAssert
                        .assertThat(productTitle)
                        .as("Product title should contains search term")
                        .contains(searchTerm.toUpperCase());
            }
            homePage = header.openHomePage();
            assertThat(homePage.isOpened())
                    .as("Home page should be opened")
                    .isTrue();
        }
        softAssert.assertAll();
    }
}