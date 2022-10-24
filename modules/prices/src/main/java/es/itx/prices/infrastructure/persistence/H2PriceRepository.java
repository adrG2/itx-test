package es.itx.prices.infrastructure.persistence;

import es.itx.prices.domain.DateRange;
import es.itx.prices.domain.Price;
import es.itx.prices.domain.PriceRate;
import es.itx.prices.domain.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class H2PriceRepository implements PriceRepository {

    private final JpaPriceRepository jpaPriceRepository;

    public H2PriceRepository(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public List<PriceRate> matching(String productId, String brandId) {
        final var prices =
                jpaPriceRepository.findByProductIdAndBrandId(productId, Long.valueOf(brandId));
        return prices.stream()
                .map(this::buildPrice)
                .collect(Collectors.toList());
    }

    private PriceRate buildPrice(PriceEntity priceEntity) {
        return new PriceRate(
                priceEntity.getId().toString(),
                priceEntity.getBrandId().toString(),
                priceEntity.getProductId(),
                new DateRange(priceEntity.getStartDate(), priceEntity.getEndDate()),
                priceEntity.getPriority(),
                new Price(priceEntity.getPrice(), Currency.getInstance(priceEntity.getCurrency())));
    }
}
