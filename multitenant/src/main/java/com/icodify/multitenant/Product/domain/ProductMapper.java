package com.icodify.multitenant.Product.domain;

import com.icodify.multitenant.Product.dto.ProductDto;
import com.icodify.multitenant.model.entities.Product;
import com.icodify.multitenant.model.entities.Warehouse;

import java.math.BigDecimal;
import java.util.Optional;

public class ProductMapper {

    public static ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableAmount(getAvailableAmount(product))
                .build();
    }

    private static BigDecimal getAvailableAmount(Product product) {
        return Optional.ofNullable(product)
                .map(Product::getWarehouse)
                .map(Warehouse::getAmount)
                .orElse(BigDecimal.ZERO);
    }
}
