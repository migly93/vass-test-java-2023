package com.vass.vasstest.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "PRICE")
public class Price {

    @Id
    private Long id;
    @ManyToOne
    private Brand brand;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    @ManyToOne
    private Product product;
    private Long priority;
    private Double finalPrice;
    private String currency;
}