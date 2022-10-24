package es.itx.prices.domain;

import java.util.List;

public interface PriceRepository {

    List<PriceRate> matching(String productId, String brandId);
}
