package com.ePay.model;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bid;
	private String name;
	private String mobileNo;
	private String relation;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "cusomerId")
	private Customer customer;

	public void setCustomer(Customer existingCustomer) {
		this.customer = existingCustomer;
	}

	// {"name":"rajan","mobileNo":"9999999999","relationWithCustomer":"Father"}

}