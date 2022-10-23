package es.itx.shop.rest;

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
    public void shouldReturnBadRequestWhenBadFormatDateParam() throws Exception {
        var expected =
                "{\"codeResponse\":\"INVALID_FORMAT\",\"message\":\"Text '2020-12-31%21:00:09' could not be parsed at index 10\"}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=15&date=2020-12-31%21:00:09"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expected));
    }

    @Test
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
    public void shouldReturnPriceFor2() throws Exception {
        var expected =
                "{\"codeResponse\":\"PRICE_NOT_FOUND\",\"message\":\"Not found price for:[productId:35455, brandId:15, date:2020-06-14T16:00\"}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-06-14T16:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void shouldReturnPriceFor3() throws Exception {
        var expected =
                "{\"codeResponse\":\"PRICE_NOT_FOUND\",\"message\":\"Not found price for:[productId:35455, brandId:15, date:2020-06-14T21:00\"}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-06-14T21:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void shouldReturnPriceFor4() throws Exception {
        var expected =
                "{\"codeResponse\":\"PRICE_NOT_FOUND\",\"message\":\"Not found price for:[productId:35455, brandId:15, date:2020-06-15T10:00\"}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-06-15T10:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    public void shouldReturnPriceFor5() throws Exception {
        var expected =
                "{\"codeResponse\":\"PRICE_NOT_FOUND\",\"message\":\"Not found price for:[productId:35455, brandId:15, date:2020-06-15T21:00:00\"}";
        this.mockMvc
                .perform(get("/prices?productId=35455&brandId=1&date=2020-06-15T21:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}
