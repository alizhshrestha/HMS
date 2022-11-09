package com.icodify.multitenant.Product.domain;

import com.icodify.multitenant.Product.dto.ProductDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
class ProductService {

	private ProductRepository productRepository;
	
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDto> getAllProducts() {
		return productRepository.findAll().stream()
				.map(ProductMapper::mapToDto)
				.collect(toList());
	}
	
	
}
