package com.ss.ita.rozetka.pageobject.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

@RequiredArgsConstructor
public class Product {

    @NonNull
    private final String xPath;
    private String promoLabelTitle;
    private String productTitle;
    private int productOldPrice;
    private int productPrice;
    private List<String> availableColors;
    private String availability;
    private int reviewAmount;
    private String productDescription;

    @Step("Product: get product title")
    public String getTitle() {
        productTitle = $x(String.format("%s%s", xPath, "//span[@class='goods-tile__title']")).text();
        return productTitle;
    }

    @Step("Product: get promo label title")
    public String getPromoLabelTitle() {
        SelenideElement promoLabelElement = $x(String.format("%s%s", xPath, "//span[contains(@class,'goods-tile__label')]"));
        if (promoLabelElement.exists()) {
            promoLabelTitle = promoLabelElement.text();
        } else {
            promoLabelTitle = StringUtils.EMPTY;
        }
        return promoLabelTitle;
    }

    @Step("Product: get available colors")
    public List<String> getAvailableColors() {
        List<SelenideElement> availableColorsList = $$x(String.format("%s%s", xPath, "//span[@class='goods-tile__colors-content']"));
        if (availableColorsList.isEmpty()) {
            availableColors = new ArrayList<>();
        } else {
            availableColors = availableColorsList
                    .stream()
                    .map(element -> Color.fromString(element.getCssValue("background-color")).asHex())
                    .collect(Collectors.toList());
        }
        return availableColors;
    }

    @Step("Product: get product old price")
    public int getOldPrice() {
        String oldPriceString = $x(String.format("%s%s", xPath, "//div[contains(@class,'goods-tile__price--old')]"))
                .text()
                .replaceAll("\\D", StringUtils.EMPTY);
        if (oldPriceString.isEmpty()) {
            productOldPrice = 0;
        } else {
            productOldPrice = Integer.parseInt(oldPriceString);
        }
        return productOldPrice;
    }

    @Step("Product: get product price")
    public int getPrice() {
        String price = $x(String.format("%s%s", xPath, "//span[contains(@class,'goods-tile__price-value')]"))
                .text()
                .replaceAll("\\D", StringUtils.EMPTY);
        if (price.isEmpty()) {
            productPrice = 0;
        } else {
            productPrice = Integer.parseInt(price);
        }
        return productPrice;
    }

    @Step("Product: get product availability")
    public String getAvailability() {
        SelenideElement availabilityElement = $x(String.format("%s%s", xPath, "//div[contains(@class,'goods-tile__availability')]"));
        if (availabilityElement.exists()) {
            availability = availabilityElement.text();
        } else {
            availability = StringUtils.EMPTY;
        }
        return availability;
    }

    @Step("Product: get review amount ")
    public int getReviewAmount() {
        SelenideElement reviewAmountElement = $x(String.format("%s%s", xPath, "//span[contains(@class,'goods-tile__reviews-link')]"));
        if (reviewAmountElement.exists()) {
            reviewAmount = Integer.parseInt((reviewAmountElement)
                    .text()
                    .replaceAll("\\D", StringUtils.EMPTY));
        } else {
            reviewAmount = 0;
        }
        return reviewAmount;
    }

    @Step("Product: get product description")
    public String getDescription() {
        $x(xPath).scrollIntoView(true).hover();
        SelenideElement descriptionElement = $x(String.format("%s%s", xPath, "//*[contains(@class,'goods-tile__description')]"));
        if (descriptionElement.exists()) {
            productDescription = descriptionElement.text();
        } else {
            productDescription = StringUtils.EMPTY;
        }
        return productDescription;
    }

    @Step("ProductTypePage: get old price text color")
    public String getOldPriceTextColor() {
        return Color.fromString($x(String.format("%s%s", xPath, "//div[contains(@class,'goods-tile__price--old')]"))
                        .getCssValue("color"))
                .asHex();
    }

    @Step("ProductTypePage: get price text color")
    public String getPriceTextColor() {
        return Color.fromString($x(String.format("%s%s", xPath, "//span[contains(@class,'goods-tile__price-value')]"))
                        .getCssValue("color"))
                .asHex();
    }
}
