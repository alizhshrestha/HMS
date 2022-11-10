package com.icodify.multitenant.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name="seq_gen", sequenceName="product_seq_gen", allocationSize = 1, initialValue = 1)
@Data
@EqualsAndHashCode(callSuper=true)
public class Product extends BaseEntity{

	private String name;
	private String description;
	private BigDecimal price;
	
	@OneToOne
	private Warehouse warehouse;
	
}
