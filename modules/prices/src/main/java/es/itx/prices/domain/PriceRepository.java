package es.itx.prices.domain;

import java.time.LocalDate;
import java.util.List;

public interface PriceRepository {
    List<PriceRate> findByDate(String productId, String brandId, LocalDate date);
}
