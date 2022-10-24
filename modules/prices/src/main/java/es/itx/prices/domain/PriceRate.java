package es.itx.prices.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PriceRate(
        String priceRateId,
        String brandId,
        String productId,
        DateRange dateRange,
        int priority,
        Price price
) implements Serializable {
    public boolean isInRange(LocalDateTime date) {
        final var start = dateRange.start();
        final var end = dateRange.end();
        return start.isBefore(date) && end.isAfter(date);
    }
}