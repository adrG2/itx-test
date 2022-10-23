package es.itx.shop.controller;

import es.itx.prices.application.PriceFinder;
import es.itx.prices.application.PriceFinderQuery;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    private final PriceFinder finder;

    public PriceController(PriceFinder finder) {
        this.finder = finder;
    }

    @GetMapping("prices")
    @ResponseBody()
    public PriceResponse findPrice(
            @RequestParam(name = "productId") String productId,
            @RequestParam(name = "brandId") String brandId,
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime date) {
        final var query = new PriceFinderQuery(productId, brandId, date);
        final var response = finder.find(query);
        return PriceResponse.from(response);
    }
}
