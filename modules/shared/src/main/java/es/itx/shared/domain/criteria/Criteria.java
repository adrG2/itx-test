package es.itx.shared.domain.criteria;

import java.util.List;
import java.util.Optional;

public record Criteria (
    List<Filter> filters,
    Order             order,
    Optional<Integer> limit,
    Optional<Integer> offset
) {}
