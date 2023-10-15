package com.vass.vasstest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vass.vasstest.dtos.PriceDto;

@SpringBootTest
class PriceControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void test1() throws Exception {
        PriceDto expectedResult = PriceDto.builder()
                                          .productId(35455L)
                                          .brandId(1L)
                                          .priceList(1L)
                                          .startDate(LocalDateTime.of(2020, 6, 14, 0 , 0, 0))
                                          .endDate(LocalDateTime.of(2020, 12, 31, 23 , 59, 59))
                                          .finalPrice(35.5)
                                          .build();
        testSuccessfulResponse(LocalDateTime.of(2020, 6, 14, 10 ,0 , 0), expectedResult);
    }

    @Test
    void test2() throws Exception {
        PriceDto expectedResult = PriceDto.builder()
                                          .productId(35455L)
                                          .brandId(1L)
                                          .priceList(2L)
                                          .startDate(LocalDateTime.of(2020, 6, 14, 15 , 0, 0))
                                          .endDate(LocalDateTime.of(2020, 6, 14, 18 , 30, 0))
                                          .finalPrice(25.45)
                                          .build();
        testSuccessfulResponse(LocalDateTime.of(2020, 6, 14, 16 ,0 , 0), expectedResult);
    }

    @Test
    void test3() throws Exception {
        PriceDto expectedResult = PriceDto.builder()
                                          .productId(35455L)
                                          .brandId(1L)
                                          .priceList(1L)
                                          .startDate(LocalDateTime.of(2020, 6, 14, 0 , 0, 0))
                                          .endDate(LocalDateTime.of(2020, 12, 31, 23 , 59, 59))
                                          .finalPrice(35.5)
                                          .build();
        testSuccessfulResponse(LocalDateTime.of(2020, 6, 14, 10 ,0 , 0), expectedResult);
    }

    @Test
    void test4() throws Exception {
        PriceDto expectedResult = PriceDto.builder()
                                          .productId(35455L)
                                          .brandId(1L)
                                          .priceList(3L)
                                          .startDate(LocalDateTime.of(2020, 6, 15, 0 , 0, 0))
                                          .endDate(LocalDateTime.of(2020, 6, 15, 11 , 0, 0))
                                          .finalPrice(30.5)
                                          .build();
        testSuccessfulResponse(LocalDateTime.of(2020, 6, 15, 10 ,0, 0), expectedResult);
    }

    @Test
    void test5() throws Exception {
        PriceDto expectedResult = PriceDto.builder()
                                          .productId(35455L)
                                          .brandId(1L)
                                          .priceList(4L)
                                          .startDate(LocalDateTime.of(2020, 6, 15, 16 , 0, 0))
                                          .endDate(LocalDateTime.of(2020, 12, 31, 23 , 59, 59))
                                          .finalPrice(38.95)
                                          .build();
        testSuccessfulResponse(LocalDateTime.of(2020, 6, 16, 21 ,0 , 0), expectedResult);
    }

    @Test
    void testMissingRequestParamProductId() throws Exception {
        testBadRequest("/price");
    }

    @Test
    void testMissingRequestParamBrandId() throws Exception {
        testBadRequest("/price?productId=1");
    }

    @Test
    void testMissingRequestParamPriceListApplicationDate() throws Exception {
        testBadRequest("/price?productId=1&brandId=1");
    }

    @Test
    void testNoDataFound() throws Exception {
        testBadRequest("/price?productId=35455&brandId=1&priceListApplicationDate=2022-06-14T10:00:00");
    }

    private void testSuccessfulResponse(LocalDateTime inputDateTime, PriceDto expectedResult) throws Exception {
        String result = mockMvc.perform(get("/price?productId=35455&brandId=1&priceListApplicationDate=" + inputDateTime))
                               .andExpect(status().is2xxSuccessful())
                               .andReturn()
                               .getResponse()
                               .getContentAsString();
        PriceDto actualResult = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(result, PriceDto.class);
        assertEquals(expectedResult, actualResult);
    }

    private void testBadRequest(String url) throws Exception {
        mockMvc.perform(get(url)).andExpect(status().isBadRequest());
    }
}