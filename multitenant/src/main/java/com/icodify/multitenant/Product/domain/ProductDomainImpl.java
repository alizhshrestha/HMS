package com.icodify.multitenant.Product.domain;

import com.icodify.multitenant.Product.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ProductDomainImpl implements ProductDomain{

    private ProductService productService;

    public ProductDomainImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
