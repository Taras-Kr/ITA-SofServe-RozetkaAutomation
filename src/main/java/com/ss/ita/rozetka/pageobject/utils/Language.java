package com.ss.ita.rozetka.pageobject.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Language {
    UA("UA"),
    RU("RU");
    @Getter
    private final String language;
}
