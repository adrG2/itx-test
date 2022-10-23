package es.itx.prices.application;

import es.itx.prices.domain.DateRange;
import es.itx.prices.domain.Price;
import es.itx.prices.domain.PriceRate;

public record PriceFinderResponse(
        String productId,
        String brandId,
        DateRange dateRange,
        Price price
) {
    public static PriceFinderResponse fromPriceRate(PriceRate priceRate) {
        return new PriceFinderResponse(priceRate.productId(), priceRate.brandId(), priceRate.dateRange(), priceRate.price());
    }
}
