package com.vass.vasstest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "PRODUCT")
public class Product {

    @Id
    private Long id;
    private String name;
}