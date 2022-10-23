package es.itx.shop.controller;

import es.itx.prices.application.PriceFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;

@RestController
public class GetPriceController {

    private final PriceFinder finder;

    @Autowired
    public GetPriceController(PriceFinder finder) {
        this.finder = finder;
    }

    // Request: fecha de aplicación(Fecha entre START_DATE o END_DATE), identificador de
    // producto(PRODUCT_ID), identificador de cadena(BRAND_ID).
    @GetMapping("prices")
    public PriceResponse findPrice(@RequestParam HashMap<String, Serializable> params) {
        // Coger los params y validar
        // Llamar al service(use case)
        finder.find(null);
        return new PriceResponse(null, null, null, null, null);
    }
}
