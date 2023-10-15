package com.vass.vasstest.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vass.vasstest.dtos.PriceDto;
import com.vass.vasstest.entities.Price;
import com.vass.vasstest.exceptions.NoDataFoundException;
import com.vass.vasstest.repositories.PriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    /**
     * Retrieves a {@link Price} from the database for the provided productId and brandId, with the priceListApplicationDate included
     * in the range of its start and end date.
     * @param productId Id of the product.
     * @param brandId Id of the brand.
     * @param priceListApplicationDate Date of the tariff to apply.
     * @return A {@link PriceDto} with the information from the retrieved data.
     * @throws NoDataFoundException If nothing is found.
     */
    public PriceDto findByProductIdAndBrandIdAndDateBetweenStartDateAndEndDate(Long productId, Long brandId, LocalDateTime priceListApplicationDate) {
        Optional<Price> priceOptional = priceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, priceListApplicationDate, priceListApplicationDate);
        return new PriceDto(priceOptional
            .orElseThrow(() ->
                new NoDataFoundException(String.format("No data found for product id: %d and brand id: %d with price application date: %s", productId, brandId, priceListApplicationDate)))
        );
    }


}