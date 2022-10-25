package es.itx.shop.persistence;

import es.itx.prices.infrastructure.persistence.H2PriceRepository;
import es.itx.prices.infrastructure.persistence.JpaPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class H2PriceRepositoryTest {

    private H2PriceRepository h2PriceRepository;

    @Autowired private JpaPriceRepository jpaPriceRepository;

    @BeforeEach
    void setUp() {
        h2PriceRepository = new H2PriceRepository(jpaPriceRepository);
    }

    @Test
    @DisplayName(
            "[H2 price repository] - should return all prices for productId 35455 and brandId 1")
    void shouldReturnPricesWhenProductIdAndBrandId() {
        final var result = h2PriceRepository.matching("35455", "1");
        assertEquals(4, result.size());
    }

    @Test
    @DisplayName("[H2 price repository] - should not return anything for productId 3 and brandId 1")
    void shouldNotReturnWhenProductIdNoExists() {
        final var result = h2PriceRepository.matching("3", "1");
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName(
            "[H2 price repository] - should not return anything for productId 35455 and brandId 2")
    void shouldNotReturnWhenBrandIdNoExists() {
        final var result = h2PriceRepository.matching("35455", "2");
        assertEquals(0, result.size());
    }
}
