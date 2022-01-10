package com.ss.ita.rozetka.pageobject.modals;

import com.codeborne.selenide.Condition;
import com.ss.ita.rozetka.pageobject.elements.Header;
import com.ss.ita.rozetka.pageobject.pages.own_cabinet.WishListPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SideMenuModal {

    @Step("SideMenuModal: close side menu  modal")
    public Header closeSideMenuModal() {
        $x("//button[@class='side-menu__close']").click();
        $x("//nav[@class='drawer ng-star-inserted']").shouldNotBe(Condition.visible);
        return new Header();
    }

    @Step("SideModalMenu: close side modal menu")
    public Header closeHamburgerModal() {
        $x("//button[@class='side-menu__close']").click();
        return new Header();
    }

    @Step("SideModalMenu: start changing city")
    public ChangeCityModal startChangingCity() {
        $x("//button[contains(@class, 'city-toggle')]").click();
        return new ChangeCityModal();
    }

    @Step("SideModalMenu: get city")
    public String getCity() {
        return $x("//span[contains(@class, 'city-toggle')]").shouldNotBe(Condition.empty).text();
    }

    @Step("Header: open wish list")
    public WishListPage openWishList() {
        $("ul.ng-star-inserted>li.side-menu__item:nth-child(1) > a.button:nth-child(1)").click();
        return new WishListPage();
    }
}
