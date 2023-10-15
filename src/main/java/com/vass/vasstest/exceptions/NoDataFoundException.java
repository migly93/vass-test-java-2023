package com.vass.vasstest.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NoDataFoundException extends RuntimeException {
    private final String message;
}