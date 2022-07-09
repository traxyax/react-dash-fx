package com.pultrax.reactdashfx.sale.interfaces;

import java.time.LocalDate;

public interface IUnitPriceAndTotalQuantityByDateAndUnitPrice {
    int getUnitPrice();
    int getTotalQuantity();
    LocalDate getDate();
}
