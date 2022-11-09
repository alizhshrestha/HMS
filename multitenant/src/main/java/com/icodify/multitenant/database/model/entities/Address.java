package com.icodify.multitenant.database.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="seq_gen", sequenceName="address_seq_gen", allocationSize = 1, initialValue = 1)
@Data
@EqualsAndHashCode(callSuper=true)
public class Address extends BaseEntity{

	private String street;
	private String houseNo;
	private String flatNo;
	private String zipCode;
	private String city;
	private String country;
	
}
