package com.vass.vasstest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vass.vasstest.dtos.ErrorResponse;

@SpringBootTest
class ControllerExceptionHandlerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testMissingServletRequestParameterException() throws Exception {
        testExceptionHandler("/price", BAD_REQUEST, new ErrorResponse("Missing request parameter: productId"));
    }

    @Test
    void testNoDataFoundException() throws Exception {
        testExceptionHandler("/price?productId=35455&brandId=1&priceListApplicationDate=2022-06-14T10:00:00",
            BAD_REQUEST,
            new ErrorResponse("No data found for product id: 35455 and brand id: 1 with price application date: 2022-06-14T10:00"));
    }

    @Test
    void testGenericException() throws Exception {
        testExceptionHandler("/price?productId=one", INTERNAL_SERVER_ERROR, new ErrorResponse("Internal server error."));
    }

    private void testExceptionHandler(String url, HttpStatus expectedStatus, ErrorResponse expectedResult) throws Exception {
        String result = mockMvc.perform(get(url))
                               .andExpect(status().is(expectedStatus.value()))
                               .andReturn()
                               .getResponse()
                               .getContentAsString();
        ErrorResponse actualResult = new ObjectMapper().readValue(result, ErrorResponse.class);
        assertEquals(expectedResult, actualResult);
    }
}