package es.itx.prices.application;

import es.itx.prices.domain.Price;
import es.itx.prices.infrastructure.H2PriceRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceRateFinderTest {

    @Test
    public void should_return_zero_price() {
        final var finder = new PriceFinder(new H2PriceRepository());
        final var priceRate = finder.find(new PriceFinderQuery(LocalDate.now(), "", ""));
        assertEquals(new Price("0.0", Currency.getInstance("EUR")), priceRate.price());
    }
}
