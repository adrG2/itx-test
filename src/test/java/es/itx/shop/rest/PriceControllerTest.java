package es.itx.shop.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    public void shouldReturnBadRequestWhenNoParams() throws Exception {
        this.mockMvc.perform(get("/prices")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestWhenNoProductIdParam() throws Exception {
        this.mockMvc
                .perform(get("/prices?brandId=1&date=2020-12-31T21:00:09"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestWhenNoBrandIdParam() throws Exception {
        this.mockMvc
                .perform(get("/prices?productId=35455&date=2020-12-31T21:00:09"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestWhenNoDateParam() throws Exception {
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("[Price controller test] - should return not found when date is out of range")
    public void shouldReturnNotFoundWhenDateIsOutOfRange() throws Exception {
        var expected =
                "{\"codeResponse\":\"PRICE_NOT_FOUND\",\"message\":\"Not found price for:[productId:35455, brandId:1, date:2020-05-14T10:00\"}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-05-14T10:00:00"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[Price controller test] - should return not found when productId not exists")
    public void shouldReturnNotFoundWhenProductIdNotExists() throws Exception {
        var expected =
                "{\"codeResponse\":\"PRICE_NOT_FOUND\",\"message\":\"Not found price for:[productId:3, brandId:1, date:2020-05-16T10:00\"}";
        this.mockMvc
                .perform(get("/prices?productId=3&brandId=1&date=2020-05-16T10:00:00"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[Price controller test] - should return not found when brandId not exists")
    public void shouldReturnNotFoundWhenBrandIdNotExists() throws Exception {
        var expected =
                "{\"codeResponse\":\"PRICE_NOT_FOUND\",\"message\":\"Not found price for:[productId:35455, brandId:1234324, date:2020-05-16T10:00\"}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1234324&date=2020-05-16T10:00:00"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[Price controller test] - should return bad request when bad format date")
    public void shouldReturnBadRequestWhenBadFormatDateParam() throws Exception {
        var expected =
                "{\"codeResponse\":\"INVALID_FORMAT\",\"message\":\"Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDateTime'; nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type [java.lang.String] to type [@org.springframework.web.bind.annotation.RequestParam @org.springframework.format.annotation.DateTimeFormat java.time.LocalDateTime] for value '2020-12-31%21:00:09'; nested exception is java.lang.IllegalArgumentException: Parse attempt failed for value [2020-12-31%21:00:09]\"}\n";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=15&date=2020-12-31%21:00:09"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[Price controller test] - should return price for 2020-06-14T10:00:00")
    public void shouldReturnPriceFor1() throws Exception {
        var expected =
                "{\"productId\":\"35455\",\"brandId\":\"1\",\"startDate\":\"2020-06-14T00:00:00\",\"endDate\":\"2020-12-31T23:59:59\",\"price\":{\"amount\":\"35.50\",\"currency\":\"EUR\"}}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-06-14T10:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[Price controller test] - should return price for 2020-06-14T16:00:00")
    public void shouldReturnPriceFor2() throws Exception {
        var expected =
                "{\"productId\":\"35455\",\"brandId\":\"1\",\"startDate\":\"2020-06-14T15:00:00\",\"endDate\":\"2020-06-14T18:30:00\",\"price\":{\"amount\":\"25.45\",\"currency\":\"EUR\"}}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-06-14T16:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[Price controller test] - should return price for 2020-06-14T21:00:00")
    public void shouldReturnPriceFor3() throws Exception {
        var expected =
                "{\"productId\":\"35455\",\"brandId\":\"1\",\"startDate\":\"2020-06-14T00:00:00\",\"endDate\":\"2020-12-31T23:59:59\",\"price\":{\"amount\":\"35.50\",\"currency\":\"EUR\"}}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-06-14T21:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[Price controller test] - should return price for 2020-06-15T10:00:00")
    public void shouldReturnPriceFor4() throws Exception {
        var expected =
                "{\"productId\":\"35455\",\"brandId\":\"1\",\"startDate\":\"2020-06-15T00:00:00\",\"endDate\":\"2020-06-15T11:00:00\",\"price\":{\"amount\":\"30.50\",\"currency\":\"EUR\"}}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-06-15T10:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @DisplayName("[Price controller test] - should return price for 2020-06-15T21:00:00")
    public void shouldReturnPriceFor5() throws Exception {
        var expected =
                "{\"productId\":\"35455\",\"brandId\":\"1\",\"startDate\":\"2020-06-15T16:00:00\",\"endDate\":\"2020-12-31T23:59:59\",\"price\":{\"amount\":\"38.95\",\"currency\":\"EUR\"}}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-06-15T21:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}
