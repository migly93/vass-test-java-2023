package com.vass.vasstest.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vass.vasstest.services.PriceService;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;
    @InjectMocks
    private PriceController priceController;

    @Test
    void testGetPrice() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime now = LocalDateTime.now();

        priceController.getPrice(productId, brandId, now);

        verify(priceService, times(1))
            .findByProductIdAndBrandIdAndDateBetweenStartDateAndEndDate(productId, brandId, now);
    }
}