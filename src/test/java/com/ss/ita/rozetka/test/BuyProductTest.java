package com.ss.ita.rozetka.test;

import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.ss.ita.rozetka.pageobject.product.GeneralProductCategory.NOTEBOOKS_AND_COMPUTERS;
import static com.ss.ita.rozetka.pageobject.product.ProductCategoryAndSubCategory.NOTEBOOKS_CATEGORY;
import static com.ss.ita.rozetka.pageobject.utils.Language.UA;
import static com.ss.ita.rozetka.pageobject.utils.PageUtil.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class BuyProductTest extends TestRunner {

    @Test
    @Description("Verify that system does not allow user to buy product with invalid data")
    @TmsLink(value = "LVTAQC630-38")
    public void verifyUserCantBuyProductWithInvalidData() {
        var homePage = new HomePage()
                .open()
                .getHeader()
                .changeLanguage(UA)
                .openHomePage();

        assertThat(getCurrentUrl())
                .as("Url should contain '/ua/'")
                .contains("/ua/");

        var productCategoryPage = homePage.openProductCategoryPage(NOTEBOOKS_AND_COMPUTERS);

        var productPage = productCategoryPage
                .openProductTypePage(NOTEBOOKS_CATEGORY)
                .openProductPage(1);

        var orderingPage = productPage
                .addProductToBasket()
                .openOrderingPage();

        orderingPage.setRequiredFields("TestUserSurname", "TestUserName", "093123456");

        assertThat(orderingPage.isSurnameErrorMessageDisplayed())
                .as("Error message should be visible")
                .isTrue();
        assertThat(orderingPage.isNameErrorMessageDisplayed())
                .as("Error message should be visible")
                .isTrue();
        assertThat(orderingPage.isPhoneNumberErrorMessageDisplayed())
                .as("Error message should be visible")
                .isTrue();

        var urlBeforeConfirmingOrder = getCurrentUrl();

        orderingPage.confirmOrdering();

        var urlAfterConfirmingOrder = getCurrentUrl();

        assertThat(urlBeforeConfirmingOrder)
                .as("Url should not be different")
                .isEqualTo(urlAfterConfirmingOrder);
    }
}
