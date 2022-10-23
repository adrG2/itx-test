package es.itx.shop.controller;

import es.itx.prices.application.PriceFinderResponse;
import es.itx.prices.domain.Price;

import java.time.LocalDateTime;

public record PriceResponse(
        String productId,
        String brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Price price
) {
    public static PriceResponse from(PriceFinderResponse response) {
        return new PriceResponse(
                response.productId(), response.brandId(), response.dateRange().start(), response.dateRange().end(), response.price()
        );
    }
}
