package es.itx.prices.application;

import es.itx.prices.domain.DateRange;
import es.itx.prices.domain.Price;
import es.itx.prices.domain.PriceRate;
import es.itx.prices.domain.PriceRepository;
import es.itx.prices.domain.exceptions.PriceNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceRateFinderTest {

    private PriceFinder finder;

    @Mock
    private PriceRepository priceRepository;

    @BeforeEach
    void init() {
        finder = new PriceFinder(priceRepository);
    }

    @Test
    public void shouldThrowPriceNotFound() {
        final var now = LocalDateTime.now();
        shouldThrowPriceNotFound(now);
        final var query = new PriceFinderQuery("", "", now);
        Assertions.assertThrows(PriceNotFound.class, () -> finder.find(query));
    }

    private void shouldThrowPriceNotFound(LocalDateTime now) {
        when(priceRepository.matching(anyString(), anyString()))
                .thenThrow(new PriceNotFound(anyString(), anyString(), now));
    }

    @Test
    public void shouldReturnPriceWhenDateItsInRange() {
        final var productId = "35455";
        final var brandId = "1";
        final var priceRate = buildPriceRate(productId, brandId);
        when(priceRepository.matching(productId, brandId)).thenReturn(List.of(priceRate));

        final var fourDaysAgo = LocalDateTime.now().minus(Duration.ofDays(4));
        final var query = new PriceFinderQuery(productId, brandId, fourDaysAgo);
        final var expected = PriceFinderResponse.fromPriceRate(priceRate);
        Assertions.assertEquals(expected, finder.find(query));
    }

    private PriceRate buildPriceRate(String productId, String brandId) {
        final var now = LocalDateTime.now();
        final var sixDaysAgo = now.minus(Duration.ofDays(6));
        final var price = new Price("13.45", Currency.getInstance("EUR"));
        return new PriceRate("1", brandId, productId, new DateRange(sixDaysAgo, now), 0, price);
    }
}
