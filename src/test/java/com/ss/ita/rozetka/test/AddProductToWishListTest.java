package com.ss.ita.rozetka.test;

import com.ss.ita.rozetka.CredentialProperties;
import com.ss.ita.rozetka.pageobject.pages.HomePage;
import com.ss.ita.rozetka.pageobject.product.GeneralProductCategory;
import com.ss.ita.rozetka.pageobject.product.ProductCategoryAndSubCategory;
import com.ss.ita.rozetka.pageobject.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AddProductToWishListTest extends TestRunner {

    @Test
    @Description("Verify user can add product to wish list")
    @TmsLink(value = "LVTAQC630-57")
    public void verifyUserCanAddProductToWishList() {
        var header = new HomePage()
                .open()
                .getHeader();

        var credentialProperties = new CredentialProperties();

        var homePage = header
                .openLoginModal()
                .loginWithFacebook(credentialProperties.getFacebookEmail(), credentialProperties.getFacebookPassword());

        var productTypePage = homePage
                .openProductCategoryPage(GeneralProductCategory.NOTEBOOKS_AND_COMPUTERS)
                .openProductTypePage(ProductCategoryAndSubCategory.NOTEBOOKS_CATEGORY);

        var productTitle = productTypePage.getProductTitle(1);

        productTypePage
                .openProductPage(1)
                .addProductToFavourite();

        var wishListPage = header.openSideMenuModal().openWishList();

        var productTitleInWishList = wishListPage.getProductTitle(1);

        assertThat(productTitle)
                .as("Product title should be the same")
                .isEqualTo(productTitleInWishList);

        var productsListSize = wishListPage.getProductsListSize();

        assertThat(productsListSize)
                .as("ProductsListSize should be greater that 0")
                .isGreaterThan(0);

        wishListPage
                .selectProduct(1)
                .removeProduct();

        assertThat(wishListPage.isProductListEmpty())
                .as("Product list size should be empty")
                .isTrue();
    }
}
