package com.ss.ita.rozetka.pageobject.pages;

import com.ss.ita.rozetka.pageobject.product.ProductCategoryAndSubCategory;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.ss.ita.rozetka.pageobject.utils.PageUtil.isElementVisible;
import static java.lang.String.format;

public class ProductCategoryPage extends HeaderPage {

    @Step("ProductCategoryPage: open product type page by category {categoryOrSubCategory}")
    public ProductTypePage openProductTypePage(ProductCategoryAndSubCategory categoryOrSubCategory) {
        $x(format("//div[contains(@class,'tile-cats')]//a[contains(@href,'%s')]", categoryOrSubCategory.getName())).click();
        return new ProductTypePage();
    }

    @Step("ProductCategoryPage: get product category page visibility status by locating page heading")
    public boolean isOpened() {
        return isElementVisible("//h1[@class = 'portal__heading ng-star-inserted']");
    }
}