package com.ss.ita.rozetka.pageobject.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Color {
    GRAY("#797878"),
    RED("#f84147");

    private final String colorAsHex;

    @Override
    public String toString() {
        return colorAsHex;
    }
}
