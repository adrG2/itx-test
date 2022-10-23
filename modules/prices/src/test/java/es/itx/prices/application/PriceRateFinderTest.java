package es.itx.prices.application;

import es.itx.prices.domain.PriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

public class PriceRateFinderTest {

    @InjectMocks private PriceFinder finder;

    @Mock private PriceRepository priceRepository;

    @Test
    public void should_return_zero_price() {
        final var priceRate = finder.find(new PriceFinderQuery("", "", LocalDateTime.now()));
    }
}
