package com.icodify.multitenant.Product.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductDto {

    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal availableAmount;
}
