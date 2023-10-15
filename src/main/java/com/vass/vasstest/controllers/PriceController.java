package com.vass.vasstest.controllers;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vass.vasstest.dtos.PriceDto;
import com.vass.vasstest.services.PriceService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/price", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping
    @Operation(summary = "Retrieves a Price from the database for the provided productId and brandId, with the priceListApplicationDate included " +
        "in the range of its start and end date.")
    public PriceDto getPrice(
        @RequestParam Long productId,
        @RequestParam Long brandId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime priceListApplicationDate) {
        return priceService.findByProductIdAndBrandIdAndDateBetweenStartDateAndEndDate(productId, brandId, priceListApplicationDate);
    }
}