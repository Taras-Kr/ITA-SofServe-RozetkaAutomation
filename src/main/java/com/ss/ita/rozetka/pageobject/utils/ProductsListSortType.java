package com.ss.ita.rozetka.pageobject.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductsListSortType {

    CHEAP_TO_EXPENSIVE("cheap to expensive", "1: cheap"),
    EXPENSIVE_TO_CHEAP("expensive to cheap", "2: expensive"),
    BY_POPULARITY("by popularity", "3: popularity"),
    BY_NOVELTY("by novelty", "4: novelty"),
    ACTION("Action", "5: action"),
    BY_RANK("by rank", "6: rank");

    private final String nameForReport;
    @Getter
    private final String xPathValue;

    @Override
    public String toString() {
        return nameForReport;
    }
}
