package es.itx.shop;

import es.itx.prices.infrastructure.persistence.H2PriceRepository;
import es.itx.prices.infrastructure.persistence.JpaPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Collections;

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
    @DisplayName("[H2 price repository] - should ")
    void shouldReturn() {
        final var result = h2PriceRepository.matching("35455", "1", LocalDateTime.now());
        assertEquals(Collections.emptyList(), result);
    }
}
