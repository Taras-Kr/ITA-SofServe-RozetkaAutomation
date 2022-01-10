package com.ss.ita.rozetka.test;

import com.ss.ita.rozetka.pageobject.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginModalTest {
    @Test
    @Description(value = "verifies validation of login & password in LoginModal")
    @TmsLink(value = "LVTAQC630-26")
    public void verifyLoginValidationTest() {
        var loginModal = new HomePage()
                .open()
                .getHeader()
                .openLoginModal()
                .setEmail("default")
                .setPassword(StringUtils.EMPTY);

        assertThat(loginModal.isInvalidLoginIconVisible())
                .as("visibility of invalid login icon should be equal true")
                .isTrue();

        assertThat(loginModal.isInvalidPasswordIconVisible())
                .as("visibility of invalid password icon should be equal true")
                .isTrue();

        loginModal
                .setEmail("test@gmail.com")
                .setPassword("1111");

        assertThat(loginModal.isInvalidLoginIconVisible())
                .as("visibility of invalid login icon should be equal false")
                .isFalse();

        assertThat(loginModal.isInvalidPasswordIconVisible())
                .as("visibility of invalid password icon should be equal false")
                .isFalse();
    }
}
