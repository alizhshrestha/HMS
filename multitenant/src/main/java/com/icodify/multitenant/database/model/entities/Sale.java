package com.icodify.multitenant.database.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name="seq_gen", sequenceName="sale_seq_gen", allocationSize = 1, initialValue = 1)
@Data
@EqualsAndHashCode(callSuper=true)
public class Sale extends BaseEntity{

	@ManyToOne
	private Customer customer;
	@ManyToMany
	@JoinTable(
			name = "Sale_Product", 
			joinColumns = @JoinColumn(name="sale_id"),
			inverseJoinColumns = @JoinColumn(name="product_id")
	)
	private List<Product> products;
	
}
