package es.itx.prices.domain;

import java.time.LocalDate;

public record DateRange(
        LocalDate start,
        LocalDate end
) {
    public static DateRange lastFiveDays() {
        final var now = LocalDate.now();
        return new DateRange(now.minusDays(5), now);
    }
}
