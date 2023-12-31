package com.icodify.internalservice.controller;

import com.icodify.internalservice.entity.Product;
import com.icodify.internalservice.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/internal/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    @RolesAllowed({"ROLE_CUSTOMER", "ROLE_EDITOR"})
    public List<Product> list(){
        return productRepository.findAll();
    }

    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product){
        Product savedProduct = productRepository.save(product);
        URI productURI = URI.create("/product/" + savedProduct.getId());

        return ResponseEntity.created(productURI).body(savedProduct);
//        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }
}
