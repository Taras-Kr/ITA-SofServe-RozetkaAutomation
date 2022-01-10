package com.ss.ita.rozetka.pageobject.modals;

import com.ss.ita.rozetka.pageobject.elements.Header;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationModal {

    @Step("Header: close registration modal")
    public Header close() {
        $("button.modal__close > svg").click();
        return new Header();
    }

    @Step("RegistrationModal: set name {name}")
    public RegistrationModal setName(String name) {
        $("input#registerUserName").sendKeys(name);
        return this;
    }

    @Step("RegistrationModal: set surname {surname}")
    public RegistrationModal setSurname(String surname) {
        $("input#registerUserSurname").sendKeys(surname);
        return this;
    }

    @Step("RegistrationModal: set phone number {phoneNumber}")
    public RegistrationModal setPhoneNumber(String phoneNumber) {
        $("input#registerUserPhone").sendKeys(phoneNumber);
        return this;
    }

    @Step("RegistrationModal: set email {email}")
    public RegistrationModal setEmail(String email) {
        $("input#registerUserEmail").sendKeys(email);
        return this;
    }

    @Step("RegistrationModal: set password {password}")
    public RegistrationModal setPassword(String password) {
        $("input#registerUserPassword").sendKeys(password);
        return this;
    }

    @Step("RegistrationModal: is name error message displayed")
    public boolean isNameErrorMessageDisplayed() {
        return $("div.form__row:nth-child(1)>form-error>p").isDisplayed();
    }

    @Step("RegistrationModal: is surname error message displayed")
    public boolean isSurnameErrorMessageDisplayed() {
        return $("div.form__row.js-surname>form-error>p").isDisplayed();
    }

    @Step("RegistrationModal: is phone number error message displayed")
    public boolean isPhoneNumberErrorMessageDisplayed() {
        return $("div.form__row:nth-child(3)>form-error>p").isDisplayed();
    }

    @Step("RegistrationModal: is email error message displayed")
    public boolean isEmailErrorMessageDisplayed() {
        return $("div.form__row:nth-child(4)>form-error>p").isDisplayed();
    }

    @Step("RegistrationModal: confirm registration")
    public RegistrationModal confirmRegistration() {
        $("button[type='submit']").click();
        return this;
    }

    @Step("RegistrationModal: is registration modal displayed")
    public boolean isRegistrationModalIsVisible() {
        return $("div.modal__content").isDisplayed();
    }
}
