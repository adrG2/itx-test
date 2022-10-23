package es.itx.prices.application;

import es.itx.prices.domain.PriceRepository;
import es.itx.shared.domain.Service;

@Service
public final class PriceFinder {

    private final PriceRepository repository;

    public PriceFinder(PriceRepository repository) {
        this.repository = repository;
    }

    public PriceFinderResponse find(PriceFinderQuery query) {
        final var priceRates =
                repository.findByDate(query.productId(), query.brandId(), query.date());
        final var priceRate = priceRates.stream().findFirst().orElseThrow();
        return PriceFinderResponse.fromPriceRate(priceRate);
    }
}
