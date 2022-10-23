package es.itx.prices.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public record DateRange(
        LocalDateTime start,
        LocalDateTime end
) {

    public static DateRange from(Date startDate, Date endDate) {
        final var start = startDate.toInstant();
        final var end = endDate.toInstant();
        return new DateRange(
                LocalDateTime.ofInstant(start, ZoneId.systemDefault()),
                LocalDateTime.ofInstant(end, ZoneId.systemDefault())
        );
    }
}
