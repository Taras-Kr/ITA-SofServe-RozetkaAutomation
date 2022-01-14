package com.ss.ita.rozetka.pageobject.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public enum GeneralProductCategory {

    NOTEBOOKS_AND_COMPUTERS("computers-notebooks"),
    SMARTPHONES_TV_AND_ELECTRONICS("telefony-tv-i-ehlektronika"),
    PRODUCTS_FOR_HOUSE("tovary-dlya-doma"),
    HOUSEHOLD_APPLIANCES("bt"),
    COTTAGE_GARDEN_BACKYARD("dacha-sad-ogorod"),
    PHONES_AND_TV("telefony-tv-i-ehlektronika"),
    SMARTPHONE_TV_ELECTRONICS("telefony-tv-i-ehlektronika");

    @Getter
    private final String name;

    public String toString() {
        return format(this.name() + "(%s)", this.getName());//method to see elements of enum with value in allure report
    }
}
