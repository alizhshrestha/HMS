package com.icodify.multitenant.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name="seq_gen", sequenceName="warehouse_seq_gen", allocationSize = 1, initialValue = 1)
@Data
public class Warehouse{

	@Id
	@GeneratedValue(generator = "seq_gen")
	protected Integer id;

	private BigDecimal amount;

	@OneToOne(mappedBy="warehouse")
	private Product product;
	
}
