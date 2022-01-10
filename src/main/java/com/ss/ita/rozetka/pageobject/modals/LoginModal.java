package com.ss.ita.rozetka.pageobject.modals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.ss.ita.rozetka.pageobject.elements.Header;
import com.ss.ita.rozetka.pageobject.pages.HomePage;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.*;

public class LoginModal {

    @Step("LoginModal: close login modal")
    public Header close() {
        $x("//button[@class='modal__close ng-star-inserted']").click();
        return new Header();
    }

    @Step("LoginModal: get password visibility toogle")
    private SelenideElement getPasswordVisibilityToogle() {
        return $x("//button[@class='button_type_link form__toggle-password']");
    }

    @Step("LoginModal: set login {login}")
    public LoginModal setEmail(String login) {
        SelenideElement emailField = $x("//input[@type='email']");
        emailField.clear();
        emailField.sendKeys(login);
        getPasswordVisibilityToogle().click();
        return this;
    }

    @Step("LoginModal: set password {password}")
    public LoginModal setPassword(String password) {
        SelenideElement passwordField = $("#auth_pass");
        passwordField.clear();
        passwordField.sendKeys(password);
        getPasswordVisibilityToogle().click();
        return this;
    }

    @Step("LoginModal: check invalid login icon visibility")
    public boolean isInvalidLoginIconVisible() {
        return $x("//p[@class='error-message ng-star-inserted']").is(Condition.visible);
    }

    @Step("LoginModal: check invalid password icon visibility")
    public boolean isInvalidPasswordIconVisible() {
        return $x("(//div[@class='form__row_with_button']//input[contains(@class,'ng-invalid')])[last()]")
                .is(Condition.visible);
    }

    @Step("LoginModal: open registration modal")
    public RegistrationModal openRegistrationModal () {
        $(".form__row.auth-modal__form-bottom button:nth-child(2)").click();
        return new RegistrationModal();
    }

    @Step("LoginModal: set facebook email {email}")
    public LoginModal setFacebookEmail(String email) {
        $("input[type=text]").sendKeys(email);
        return this;
    }

    @Step("LoginModal: set facebook {password}")
    public LoginModal setFacebookPassword(String password) {
        $("input[type=password]").sendKeys(password);
        return this;
    }

    @Step("LoginModal: open login with facebook")
    public LoginModal startLoggingWithFacebook() {
        $("div[class='auth-modal__social'] button:nth-child(1)").click();
        return this;
    }

    @Step("LoginModal: confirm login with facebook")
    public LoginModal confirmLoginWithFacebook() {
        $("input[type=submit]").click();
        return this;
    }

    @Step("LoginModal: login with facebook {email}, {password}")
    public HomePage loginWithFacebook(String email, String password) {
        startLoggingWithFacebook();

        switchTo().window(1);

        setFacebookEmail(email)
                .setFacebookPassword(password)
                .confirmLoginWithFacebook();

        switchTo().window(0);

        return new HomePage();
    }
}
