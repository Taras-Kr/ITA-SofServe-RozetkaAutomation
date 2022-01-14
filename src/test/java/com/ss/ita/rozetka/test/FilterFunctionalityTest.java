package com.ss.ita.rozetka.test;

import com.ss.ita.rozetka.pageobject.elements.Product;
import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.pages.ProductTypePage;
import com.ss.ita.rozetka.pageobject.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.ss.ita.rozetka.pageobject.elements.filters.FilterName.*;
import static com.ss.ita.rozetka.pageobject.product.GeneralProductCategory.NOTEBOOKS_AND_COMPUTERS;
import static com.ss.ita.rozetka.pageobject.product.ProductCategoryAndSubCategory.MONITORS_CATEGORY;
import static com.ss.ita.rozetka.pageobject.product.ProductCategoryAndSubCategory.NOTEBOOKS_CATEGORY;
import static com.ss.ita.rozetka.pageobject.utils.Language.UA;
import static com.ss.ita.rozetka.pageobject.utils.PageUtil.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class FilterFunctionalityTest extends TestRunner {

    @Test
    @Description("Verify that user can filter products with parameters")
    @TmsLink(value = "LVTAQC630-22")
    public void verifyUserCanFilterProducts() {
        var productTypePage = new HomePage()
                .open()
                .openProductCategoryPage(NOTEBOOKS_AND_COMPUTERS)
                .openProductTypePage(MONITORS_CATEGORY);

        assertThat(getCurrentUrl())
                .as("Url should contain 'Monitors'")
                .contains("monitors");

        var company = "Samsung";
        var resolution = "1920x1080";

        productTypePage
                .filterProductsByParameters(company)
                .filterProductsByParameters(resolution);

        assertThat(productTypePage.getProductTitle(1))
                .as("Title should be Samsung")
                .contains(company);

        var productPage = productTypePage.openProductPage(1);

        assertThat(productPage
                .getProductCharacteristics())
                .as("In characteristics should be 1920 x 1080 resolution")
                .contains("1920 x 1080");

        var basket = productPage.addProductToBasket();

        var totalPriceWithoutOptions = basket.getProductsTotalPrice();

        basket.addAdditionalServices();

        var totalPriceWithOptions = basket.getProductsTotalPrice();

        assertThat(totalPriceWithoutOptions)
                .as("Total price should be changed")
                .isLessThan(totalPriceWithOptions);
    }

    @Test
    @Description("Verify that products quantity with two filters will be less than with one")
    @TmsLink(value = "LVTAQC630-51")
    public void verifyThatProductsQuantityWithTwoFiltersWillDecrease() {
        ProductTypePage productTypePage = new HomePage()
                .open()
                .openProductCategoryPage(NOTEBOOKS_AND_COMPUTERS)
                .openProductTypePage(MONITORS_CATEGORY);

        var filterSideBar = productTypePage.getFilterSideBar();

        filterSideBar
                .getFilter(PRODUCER)
                .selectOption("ASUS");

        var productsQuantityWithOneFilter = productTypePage.getProductsQuantity();
        System.out.println(productsQuantityWithOneFilter);

        assertThat(productsQuantityWithOneFilter)
                .as("Products quantity should be greater than 0")
                .isGreaterThan(0);

        filterSideBar
                .getFilter(SELLER)
                .selectOption("Rozetka");

        System.out.println(productTypePage.getProductsQuantity());

        assertThat(productTypePage.getProductsQuantity())
                .as("Products quantity with two filter should be less than with one")
                .isLessThan(productsQuantityWithOneFilter);
    }

    @Test
    @Description("Verify that after changing and submitting price bounds displayed products with correct price")
    @TmsLink(value = "LVTAQC630-55")
    public void verifyThatPriceFilterIsCorrect() {
        var productTypePage = new HomePage()
                .open()
                .openProductCategoryPage(NOTEBOOKS_AND_COMPUTERS)
                .openProductTypePage(NOTEBOOKS_CATEGORY);

        var company = "Dell";
        var filterSideBar = productTypePage.getFilterSideBar();

        filterSideBar
                .getFilter(PRODUCER)
                .selectOption(company);

        assertThat(productTypePage.getProductTitle(1))
                .as("Title should contains Dell")
                .contains(company);

        int minPrice = 15000;
        int maxPrice = 50000;

        filterSideBar
                .setMinPrice(minPrice)
                .setMaxPrice(maxPrice)
                .filterByPrice();

        int productPrisesCount = productTypePage.getProductsCount();
        var productPricesList = productTypePage.getProductPricesList();
        SoftAssertions softAssertion = new SoftAssertions();

        for (int i = 0; i < productPrisesCount; i++) {
            softAssertion
                    .assertThat(productPricesList.get(i))
                    .as("Price should be in selected bounds")
                    .isGreaterThan(minPrice)
                    .isLessThan(maxPrice);
        }
        softAssertion.assertAll();
    }

    @Test
    @Description("Verify that after selecting and discarding 2 filter options product type page presented correctly")
    @TmsLink(value = "LVTAQC630-66")
    public void verifyThatDiscardButtonWorksCorrectly() {
        var productTypePage = new HomePage()
                .open()
                .openProductCategoryPage(NOTEBOOKS_AND_COMPUTERS)
                .openProductTypePage(NOTEBOOKS_CATEGORY);

        var company = "Dell";
        var seller = "Rozetka";
        var filterSideBar = productTypePage.getFilterSideBar();

        filterSideBar
                .getFilter(PRODUCER)
                .selectOption(company);

        assertThat(productTypePage.getProductTitle(1))
                .as("Title should contains Dell")
                .contains(company);

        filterSideBar
                .getFilter(SELLER)
                .selectOption(seller);

        assertThat(filterSideBar
                .getFilter(PRODUCER)
                .isOptionSelected(company))
                .as("Filter should be selected")
                .isTrue();

        productTypePage.discardAllFilters();
        assertThat(productTypePage.isDiscardingButtonInvisible())
                .as("Discard all filters button should be invisible")
                .isTrue();

        assertThat(filterSideBar
                .getFilter(SELLER)
                .isOptionSelected(seller))
                .as("After discarding filter shouldn't be selected")
                .isFalse();

        assertThat(filterSideBar
                .getFilter(PRODUCER)
                .isOptionSelected(company))
                .as("After discarding filter shouldn't be selected")
                .isFalse();
    }

  
    @Test
    @TmsLink(value = "LVTAQC630-53")
    @Description(value = "Verify that after multiply filtering all products corresponds to selected filter options")
    public void verifyProductsCorrespondsToSelectedFilterOptions() {
        var productCategoryPage = new HomePage()
                .open()
                .getHeader()
                .changeLanguage(UA)
                .openHomePage()
                .openProductCategoryPage(NOTEBOOKS_AND_COMPUTERS);
        var productTypePage = productCategoryPage.openProductTypePage(NOTEBOOKS_CATEGORY);
        assertThat(productTypePage.isOpened())
                .as("Product type page should be opened")
                .isTrue();
        var filterSideBar = productTypePage.getFilterSideBar();
        var readyToDelivery = "Готовий до відправлення";
        filterSideBar
                .getFilter(READY_TO_DELIVER)
                .selectOption(readyToDelivery);
        var brand = "Dell";
        filterSideBar
                .getFilter(PRODUCER)
                .selectOption(brand);
        var screenSize = "15\"-15.6\"";
        filterSideBar
                .getFilter("20861")
                .selectOption(screenSize);
        var splitScreenSizeParams = screenSize.split("-");
        var softAssert = new SoftAssertions();
        var productList = productTypePage.getProductsList();
        for (Product productItem : productList) {
            softAssert
                    .assertThat(productItem.getTitle())
                    .as("Product title should be contains " + brand)
                    .contains(brand);
            softAssert
                    .assertThat(productItem.getDescription())
                    .as("Product description should contains screen size " + productTypePage)
                    .containsAnyOf(splitScreenSizeParams);
            softAssert
                    .assertThat(productItem.getAvailability())
                    .as("Product availability should be " + readyToDelivery)
                    .isEqualTo(readyToDelivery);
        }
        softAssert.assertAll();
    }
}
