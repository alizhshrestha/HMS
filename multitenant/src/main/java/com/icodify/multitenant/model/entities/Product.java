package com.icodify.multitenant.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name="seq_gen", sequenceName="product_seq_gen", allocationSize = 1, initialValue = 1)
@Data
public class Product{

	@Id
	@GeneratedValue(generator = "seq_gen")
	protected Integer id;

	private String name;
	private String description;
	private BigDecimal price;

	@OneToOne
	private Warehouse warehouse;
	
}
