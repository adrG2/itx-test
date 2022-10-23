package es.itx.shared.domain.criteria;

public record Filter (String field, FilterOperator operator, String value) {}
