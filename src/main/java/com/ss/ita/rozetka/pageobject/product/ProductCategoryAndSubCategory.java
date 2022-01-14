package com.ss.ita.rozetka.pageobject.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public enum ProductCategoryAndSubCategory {

    NOTEBOOKS_CATEGORY("c80004"),
    MONITORS_CATEGORY("monitors"),
    GARDEN_TECH_CATEGORY("garden_tech"),
    GARDEN_EQUIP_CATEGORY("sadoviy-inventar"),
    BIG_HOUSEHOLD_APPLIANCES_CATEGORY("bigbt"),
    KITCHEN_APPLIANCES_CATEGORY("tehnika-dlya-kuhni"),
    TV_ACCESSORIES_CATEGORY("c80015"), //specific
    SPA_POOLS_CATEGORY("spa-basseyni"),
    TRIMMERS_SUBCATEGORY("trimmers"),
    MOBILE_PHONES_CATEGORY("mobile-phones");

    @Getter
    private final String name;

    public String toString() {
        return format(this.name() + "(%s)", this.getName());//the same
    }
}