package es.itx.prices.domain;

import java.time.LocalDateTime;

public record DateRange(
        LocalDateTime start,
        LocalDateTime end
) { }
