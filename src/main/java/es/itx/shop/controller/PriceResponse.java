package es.itx.shop.controller;

import es.itx.prices.domain.Price;

import java.util.Date;

// Devuelva como datos de salida: identificador de producto(PRODUCT_ID), identificador de cadena(BRAND_ID?), tarifa
// a aplicar(PRICE_LIST), fechas de aplicación(START_DATE y END_DATE) y precio final a aplicar(PRICE).
public record PriceResponse(
        String productId,
        String brandId,
        Date startDate,
        Date endDate,
        Price price
) { }
