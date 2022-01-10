package com.ss.ita.rozetka.pageobject.pages;

import com.codeborne.selenide.Selenide;
import com.ss.ita.rozetka.pageobject.elements.Header;
import io.qameta.allure.Step;
import lombok.Getter;

public abstract class HeaderPage {
    @Getter
    protected Header header = new Header();

    @Step("Return to previous page")
    public Header back() {
        Selenide.back();
        return new Header();
    }
}
