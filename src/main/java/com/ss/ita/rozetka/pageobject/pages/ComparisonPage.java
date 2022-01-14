package com.ss.ita.rozetka.pageobject.pages;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ComparisonPage {

    @Step("ComparisonPage: get product comparison list size")
    public int getComparisonListSize() {
        return $$x("//a[@class='product__heading']")
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .size();
    }

    @Step("ComparisonPage: open ProductTypePage")
    public ProductTypePage openProductTypePage(){
        $x("(//span[@class='comparison-settings__label'])[1]").click();
        return new ProductTypePage();
    }
}
