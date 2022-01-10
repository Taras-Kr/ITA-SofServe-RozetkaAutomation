package com.ss.ita.rozetka.test;

import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.ss.ita.rozetka.pageobject.product.GeneralProductCategory.*;
import static com.ss.ita.rozetka.pageobject.product.ProductCategoryAndSubCategory.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductTypePageTest extends TestRunner {

    @Test
    @Description("Verify that at least 10 products are presented in notebook category")
    @TmsLink(value = "LVTAQC630-14")
    public void verifyThatProductsArePresentedInNotebookCategory() {
        var productTypePage = new HomePage()
                .open()
                .openProductCategoryPage(NOTEBOOKS_AND_COMPUTERS)
                .openProductTypePage(NOTEBOOKS_CATEGORY);

        assertThat(productTypePage.getProductsCount())
                .as("There should be presented at least 10 products")
                .isGreaterThan(10);
    }

    @Test
    @Description("Verify that at least 10 products are presented in TV accessories category")
    @TmsLink(value = "LVTAQC630-35")
    public void verifyThatProductsArePresentedInTVAccessoriesCategory() {
        var productTypePage = new HomePage()
                .open()
                .openProductCategoryPage(PHONES_AND_TV)
                .openProductTypePage(TV_ACCESSORIES_CATEGORY);

        assertThat(productTypePage.getProductsCount())
                .as("There should be presented at least 10 products")
                .isGreaterThan(10);
    }
}
