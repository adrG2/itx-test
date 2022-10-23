package es.itx.shared.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateConverter {
    public static Date fromLocalDateTime(LocalDateTime date) {
        final var toInstant = date.toInstant(ZoneOffset.UTC);
        return Date.from(toInstant);
    }
}
