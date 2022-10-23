package es.itx.prices.infrastructure;

import es.itx.prices.domain.PriceRate;
import es.itx.prices.domain.PriceRepository;
import es.itx.shared.domain.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class H2PriceRepository implements PriceRepository {
    @Override
    public List<PriceRate> findByDate(String productId, String brandId, LocalDate date) {
        return List.of(PriceRate.empty());
    }
}
