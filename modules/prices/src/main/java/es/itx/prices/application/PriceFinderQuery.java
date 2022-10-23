package es.itx.prices.application;

import java.time.LocalDate;

public record PriceFinderQuery(
        String productId,
        String brandId,
        LocalDate date
) { }
