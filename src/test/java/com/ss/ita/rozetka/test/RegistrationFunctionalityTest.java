package com.ss.ita.rozetka.test;

import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.assertj.core.api.Assertions.assertThat;


public class RegistrationFunctionalityTest extends TestRunner {

    @Test
    @Description("Verify that system does not allow to register new user with invalid data")
    @TmsLink(value = "LVTAQC630-46")
    public void verifyUserCantRegisterWithInvalidData() {
        var registrationModal = new HomePage()
                .open()
                .getHeader()
                .openLoginModal()
                .openRegistrationModal();

        registrationModal
                .setName("Test Name")
                .setSurname("Test Surname")
                .setPhoneNumber("09312345")
                .setEmail("Test Email");

        var softAssertion = new SoftAssertions();

        softAssertion
                .assertThat(registrationModal.isNameErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();

        softAssertion
                .assertThat(registrationModal.isSurnameErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();

        softAssertion
                .assertThat(registrationModal.isPhoneNumberErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();

        softAssertion
                .assertThat(registrationModal.isEmailErrorMessageDisplayed())
                .as("Error message should be displayed")
                .isTrue();

        registrationModal.confirmRegistration();

        softAssertion
                .assertThat(registrationModal.isRegistrationModalIsVisible())
                .as("Registration modal should be visible")
                .isTrue();

        softAssertion.assertAll();
    }
}
