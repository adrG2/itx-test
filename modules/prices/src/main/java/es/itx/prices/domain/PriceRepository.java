package es.itx.prices.domain;

import es.itx.shared.domain.criteria.Criteria;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<PriceRate> matching(Criteria criteria);

    List<PriceRate> matching(String productId, String brandId, LocalDateTime date);
}
