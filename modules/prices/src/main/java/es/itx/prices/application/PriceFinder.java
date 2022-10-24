package es.itx.prices.application;

import es.itx.prices.domain.PriceRate;
import es.itx.prices.domain.PriceRepository;
import es.itx.prices.domain.exceptions.PriceNotFound;
import es.itx.shared.domain.Service;

import java.util.Comparator;
import java.util.function.Supplier;

@Service
public final class PriceFinder {

    private final PriceRepository repository;

    public PriceFinder(PriceRepository repository) {
        this.repository = repository;
    }

    public PriceFinderResponse find(PriceFinderQuery query) {
        final var priceRateFound =
                repository.matching(query.productId(), query.brandId()).stream()
                        .filter(priceRate -> priceRate.isInRange(query.date()))
                        .max(Comparator.comparing(PriceRate::priority))
                        .orElseThrow(priceNotfound(query));
        return PriceFinderResponse.fromPriceRate(priceRateFound);
    }

    private Supplier<PriceNotFound> priceNotfound(PriceFinderQuery query) {
        return () -> new PriceNotFound(query.productId(), query.brandId(), query.date());
    }
}
