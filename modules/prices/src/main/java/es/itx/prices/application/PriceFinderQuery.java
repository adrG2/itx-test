package es.itx.prices.application;

import java.time.LocalDateTime;

public record PriceFinderQuery(
        String productId,
        String brandId,
        LocalDateTime date
) { }
