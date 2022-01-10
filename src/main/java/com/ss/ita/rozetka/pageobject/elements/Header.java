package com.ss.ita.rozetka.pageobject.elements;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.ss.ita.rozetka.pageobject.modals.*;
import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.pages.ProductTypePage;
import com.ss.ita.rozetka.pageobject.utils.Language;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class Header {

    private final String searchHistoryListXPath = "//li[@class='search-suggest__item ng-star-inserted']" ;

    @Step("Header: do search of term {searchText}")
    public ProductTypePage doSearch(String searchText) {
        return setSearchTerms(searchText).search();
    }

    @Step("Header: set search field by term {searchText}")
    public Header setSearchTerms(String searchText) {
        SelenideElement input = $("input.search-form__input");
        input.clear();
        input.sendKeys(searchText);
        return this;
    }

    @Step("Header: click on button search")
    public ProductTypePage search() {
        $("button.button_color_green").click();
        return new ProductTypePage();
    }

    @Step("Header: change language to {language}")
    public Header changeLanguage(Language language) {
        $x(String.format("//a[normalize-space()='%s']", language)).click();
        return this;
    }

    @Step("Header: get count of compared products")
    public int getProductsForComparisonCount() {
        return Integer.parseInt($x("//span[@class='counter counter--gray ng-star-inserted']")
                .shouldBe(Condition.visible)
                .getText()
                .replaceAll("\\D", StringUtils.EMPTY));
    }

    @Step("Header: open side menu modal")
    public SideMenuModal openSideMenuModal() {
        $x("//button[@class='header__button']").click();
        return new SideMenuModal();
    }

    @Step("Header: open comparison modal")
    public ComparisonModal openComparisonModal() {
        $x("(//button[@class='header__button ng-star-inserted'])[2]").click();
        return new ComparisonModal();
    }

    @Step("Header: open login modal")
    public LoginModal openLoginModal() {
        $x("//button[@class='header__button ng-star-inserted']").click();
        return new LoginModal();
    }

    @Step("Header: get catalog modal visibility status")
    public boolean isCatalogModalVisible() {
        return $(".menu-wrapper").is(Condition.visible);
    }

    @Step("Header: open catalog modal")
    public CatalogModal openCatalogModal() {
        if (!isCatalogModalVisible()) {
            $(By.id("fat-menu")).click();
        }
        return new CatalogModal();
    }

    @Step("Header: close catalog modal")
    public Header closeCatalogModal() {
        if (isCatalogModalVisible()) {
            $(By.id("fat-menu")).click();
        }
        return new Header();
    }

    @Step("Header: open basket modal")
    public BasketModal<Header> openBasketModal() {
        $x("//button[@class = 'header__button ng-star-inserted header__button--active']").click();
        return new BasketModal<>(this);
    }

    @Step("Header: open home page")
    public HomePage openHomePage() {
        $x("//a[@class='header__logo']").click();
        return new HomePage();
    }

    @Step("Header: get display status side menu modal")
    public boolean isSideMenuModalOpened() {
        return $x("//nav[@class='drawer ng-star-inserted']").isDisplayed();
    }

    @Step("Header: set focus in search input")
    public Header setSearchInputInFocus() {
        $(By.name("search")).click();
        return this;
    }

    @Step("Header: get text number {numberSearchedTerm} from search history")
    public String getTextFromSearchHistory(int numberSearchedTerm) {
        setSearchInputInFocus();
        return $x(String.format("(%s)[%s]", searchHistoryListXPath, numberSearchedTerm))
                .getText();
    }

    @Step("Header: Get search history terms list")
    public List<String> getSearchHistoryTermsList() {
        setSearchInputInFocus();
        return $$x(searchHistoryListXPath)
                .shouldHave(CollectionCondition.sizeGreaterThan(1))
                .texts();
    }

    @Step("Header: Open item number - {numberItem} from search history")
    public ProductTypePage openItemFromSearchHistory(int numberItem) {
        setSearchInputInFocus();
        $x(String.format("(%s)[%s]", searchHistoryListXPath, numberItem)).click();
        return new ProductTypePage();
    }
}
