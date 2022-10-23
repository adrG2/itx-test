package es.itx.prices.domain;

import java.io.Serializable;

public record PriceRate(
        String priceRateId,
        String brandId,
        String productId,
        DateRange dateRange,
        int priority,
        Price price
) implements Serializable {
    public static PriceRate empty() {
        return new PriceRate(null, null, null,null, 0, null);
    }
}