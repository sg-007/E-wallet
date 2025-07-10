package com.ePay.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bid;
	private String name;
	private String mobileNo;
	private String relation;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "customerId")
	private Customer customer;
}