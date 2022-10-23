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
        final var priceRates =
                repository.matching(query.productId(), query.brandId(), query.date());
        final var maxPriority = priceRates.stream().max(Comparator.comparing(PriceRate::priority));
        return PriceFinderResponse.fromPriceRate(maxPriority.orElseThrow(priceNotfound(query)));
    }

    private Supplier<PriceNotFound> priceNotfound(PriceFinderQuery query) {
        return () -> new PriceNotFound(query.productId(), query.brandId(), query.date());
    }
}
