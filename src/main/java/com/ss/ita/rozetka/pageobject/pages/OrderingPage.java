package com.ss.ita.rozetka.pageobject.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;

public class OrderingPage {

    @Step("OrderingPage: get total products price")
    public int getTotalProductsPrice() {
        return parseInt($x("//dd[@class = 'checkout-total__value checkout-total__value_size_large']")
                .text()
                .replaceAll("[^0-9]", StringUtils.EMPTY));
    }

    @Step("OrderingPage: get ordering page status")
    public boolean isOrderingPageOpened() {
        return $x("//div[@class = 'checkout-sidebar ng-star-inserted']")
                .shouldBe(Condition.visible)
                .isDisplayed();
    }

    @Step("OrderingPage: get product title")
    public String getProductTitle() {
        return $x("//div[@class = 'checkout-product__title-product']").text();
    }

    @Step("OrderingPage: set {surname}")
    public OrderingPage setSurname(String surname) {
        $("div[class='form__row js-surname'] input[type='text']").sendKeys(surname);
        return this;
    }

    @Step("OrderingPage: set {name}")
    public OrderingPage setName(String name) {
        $("div[class='form__row js-name'] input[type='text']").sendKeys(name);
        return this;
    }

    @Step("OrderingPage: set {phoneNumber}")
    public OrderingPage setPhoneNumber(String phoneNumber) {
        $("input[class='ng-invalid ng-dirty ng-touched']").sendKeys(phoneNumber);
        return this;
    }

    @Step("OrderingPage: set {surname}, {name}, {phoneNumber}")
    public OrderingPage setRequiredFields(String surname, String name, String phoneNumber) {
        setSurname(surname);
        setName(name);
        setPhoneNumber(phoneNumber);
        return this;
    }

    @Step("OrderingPage: get name error message")
    public boolean isNameErrorMessageDisplayed() {
        return $(".form__row .js-name >form-error> p").isDisplayed();
    }

    @Step("OrderingPage: get surname error message")
    public boolean isSurnameErrorMessageDisplayed() {
        return $(".form__row .js-surname >form-error> p").isDisplayed();
    }

    @Step("OrderingPage: get phoneNumber error message")
    public boolean isPhoneNumberErrorMessageDisplayed() {
        return $(".form__row .js-phone >form-error> p").isDisplayed();
    }

    @Step("OrderingPage: confirm ordering")
    public OrderingPage confirmOrdering() {
        $("input.button--green").click();
        return this;
    }

}
