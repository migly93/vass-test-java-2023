package com.vass.vasstest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vass.vasstest.entities.Brand;
import com.vass.vasstest.entities.Price;
import com.vass.vasstest.entities.Product;
import com.vass.vasstest.exceptions.NoDataFoundException;
import com.vass.vasstest.repositories.PriceRepository;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;
    @InjectMocks
    private PriceService priceService;

    @Test
    void testFindByProductIdAndBrandIdAndDateBetweenStartDateAndEndDateCallsRepository() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime now = LocalDateTime.now();

        Price mockPrice = new Price();
        mockPrice.setBrand(new Brand());
        mockPrice.setProduct(new Product());

        when(priceRepository
            .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, now, now))
            .thenReturn(Optional.of(mockPrice));

        priceService.findByProductIdAndBrandIdAndDateBetweenStartDateAndEndDate(productId, brandId, now);

        verify(priceRepository, times(1))
            .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, now, now);
    }

    @Test
    void testFindByProductIdAndBrandIdAndDateBetweenStartDateAndEndDateThrowsNoDataFoundExceptionWhenNothingIsFound() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 10, 15, 10, 0);

        NoDataFoundException exception = assertThrows(NoDataFoundException.class, () ->
            priceService.findByProductIdAndBrandIdAndDateBetweenStartDateAndEndDate(1L, 1L, localDateTime));

        assertEquals("No data found for product id: 1 and brand id: 1 with price application date: 2023-10-15T10:00", exception.getMessage());
    }
}