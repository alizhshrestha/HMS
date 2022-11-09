package com.icodify.multitenant.database.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;

@Entity
@SequenceGenerator(name="seq_gen", sequenceName="warehouse_seq_gen", allocationSize = 1, initialValue = 1)
@Data
@EqualsAndHashCode(callSuper=true)
public class Warehouse extends BaseEntity{

	private BigDecimal amount;

	@OneToOne(mappedBy="warehouse")
	private Product product;
	
}
