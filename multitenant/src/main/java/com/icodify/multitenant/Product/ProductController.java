package com.icodify.multitenant.Product;

import com.icodify.multitenant.Product.domain.ProductDomain;
import com.icodify.multitenant.Product.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
	
	private ProductDomain productDomain;
	
	public ProductController(ProductDomain productDomain) {
		this.productDomain = productDomain;
	}
	
	@GetMapping("/all")
	public List<ProductDto> getAllProductsOfClient(){
		return productDomain.getAllProducts();
	}
}
