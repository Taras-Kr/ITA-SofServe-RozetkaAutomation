package com.ss.ita.rozetka.pageobject.pages;

import com.ss.ita.rozetka.pageobject.elements.Header;
import com.ss.ita.rozetka.pageobject.modals.BasketModal;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends HeaderPage {

    @Step("ProductPage: add product to comparison")
    public Header addProductToComparison() {
        $x("//button[@class='compare-button ng-star-inserted']").click();
        return new Header();
    }

    @Step("ProductPage: add product to basket")
    public BasketModal<ProductPage> addProductToBasket() {
        $x("//button[contains(@class,'buy-button button button_')]").click();
        return new BasketModal<ProductPage>(this);
    }

    @Step("ProductPage: add product to comparison")
    public ProductPage addToComparison() {
        $x("//button[contains(@class, 'compare-button')]").click();
        return this;
    }

    @Step("ProductPage: get product price")
    public int getPrice() {
        String value = $x("//p[contains(@class, 'product-prices__big')]").text();
        return Integer.parseInt(value.replaceAll("\\D", StringUtils.EMPTY));
    }

    @Step("ProductPage: get product name")
    public String getTitle() {
        return $x("//h1[@class = 'product__title']").text();
    }

    @Step("ProductPage: get product description")
    public String getDescription() {
        return $x("//p[@class = 'product-about__brief ng-star-inserted']").text();
    }

    @Step("ProductPage: open {number}-th related product")
    public ProductPage openRelatedProduct(int number) {
        String relatedProductXpathTemplate =
                "//section[contains(@class,'product-related')]//li[contains(@class,'simple-slider__item')][%d]";
        $x(String.format(relatedProductXpathTemplate, number)).click();
        return this;
    }

    @Step("ProductPage: get product characteristics")
    public String getProductCharacteristics() {
        return $(".characteristics-full__list").getText();
    }

    @Step("ProductPage: add product to favourite")
    public ProductPage addProductToFavourite() {
        $x("(//*[name()='svg'][@class='ng-star-inserted'])[1]").click();
        return this;
    }
}
