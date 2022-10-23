package es.itx.prices.application;

public record PriceFinderQuery(
        String productId,
        String brandId,
        java.time.LocalDateTime date
) { }
