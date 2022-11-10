package com.icodify.multitenant.Product.domain;

import com.icodify.multitenant.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends JpaRepository<Product, Long> {

	
}
