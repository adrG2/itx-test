package es.itx.prices.domain;

import java.util.Currency;

public record Price(String amount, Currency currency) { }
