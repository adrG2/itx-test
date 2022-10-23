package es.itx.shop;

import es.itx.shared.domain.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        includeFilters =
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {"es.itx.prices", "es.itx.shared", "es.itx.shop"})
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}
