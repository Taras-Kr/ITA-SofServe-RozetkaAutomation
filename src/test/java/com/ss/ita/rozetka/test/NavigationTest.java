package com.ss.ita.rozetka.test;

import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.pages.ProductCategoryPage;
import com.ss.ita.rozetka.pageobject.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.ss.ita.rozetka.pageobject.product.GeneralProductCategory.HOUSEHOLD_APPLIANCES;
import static com.ss.ita.rozetka.pageobject.product.ProductCategoryAndSubCategory.KITCHEN_APPLIANCES_CATEGORY;
import static com.ss.ita.rozetka.pageobject.utils.PageUtil.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class NavigationTest extends TestRunner {

    @Test
    @Description(value = "Verifies that user can return to home page from product category page")
    @TmsLink(value = "LVTAQC630-7")
    public void verifyReturnToHomePage() {
        var productCategoryPage = new HomePage()
                .open()
                .openProductCategoryPage(HOUSEHOLD_APPLIANCES);

        var isProductPageOpened = productCategoryPage.isOpened();
        assertThat(isProductPageOpened)
                .as("Product category page should be opened")
                .isTrue();

        var isProductTypePageOpened = productCategoryPage
                .openProductTypePage(KITCHEN_APPLIANCES_CATEGORY)
                .isOpened();
        assertThat(isProductTypePageOpened)
                .as("Product type page should be opened")
                .isTrue();

        var isHomePageOpened = productCategoryPage
                .getHeader()
                .openHomePage()
                .isOpened();
        assertThat(getCurrentUrl())
                .as("Home page should be opened")
                .isEqualTo("https://rozetka.com.ua/");
        assertThat(isHomePageOpened)
                .as("Home page should be opened")
                .isTrue();
    }
}
