package com.ss.ita.rozetka.pageobject.elements.filters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public enum FilterName {
    SELLER("seller"),
    READY_TO_DELIVER("gotovo-k-otpravke"),
    PRODUCER("producer"),
    PRODUCING_COUNTRY("strana-proizvoditelj-tovara-90098"),
    COUNTRY_OF_BRAND_REGISTRATION("strana-registracii-brenda-87790"),
    SELL_STATUS("sell_status");

    @Getter
    private final String filterName;
}
