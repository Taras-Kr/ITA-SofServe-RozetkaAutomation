package com.ss.ita.rozetka.pageobject.modals;

import com.ss.ita.rozetka.pageobject.pages.ComparisonPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ComparisonModal {

    @Step("ComparisonModal: open comparison page")
    public ComparisonPage openComparisonPage() {
        $x("//a[@class='comparison-modal__link']").click();
        return new ComparisonPage();
    }
}
