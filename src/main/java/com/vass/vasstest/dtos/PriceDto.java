package com.vass.vasstest.dtos;

import java.time.LocalDateTime;

import com.vass.vasstest.entities.Price;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class PriceDto {
    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double finalPrice;

    public PriceDto(Price price) {
        this.productId = price.getProduct().getId();
        this.brandId = price.getBrand().getId();
        this.priceList = price.getPriceList();
        this.startDate = price.getStartDate();
        this.endDate = price.getEndDate();
        this.finalPrice = price.getFinalPrice();
    }
}