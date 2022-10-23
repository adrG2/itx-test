package es.itx.prices.domain.exceptions;

import java.time.LocalDateTime;

public class PriceNotFound extends RuntimeException {
    public PriceNotFound(String productId, String brandId, LocalDateTime date) {
        super(
                String.format(
                        "Not found price for:[productId:%s, brandId:%s, date:%s",
                        productId, brandId, date));
    }
}
