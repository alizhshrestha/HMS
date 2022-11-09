package com.icodify.multitenant.Product.domain;


import com.icodify.multitenant.Product.dto.ProductDto;

import java.util.List;

public interface ProductDomain {

    List<ProductDto> getAllProducts();
}
