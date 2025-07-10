package com.ePay.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@ToString
public class CustomerSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

//	@OneToOne
//	@JoinColumn(name = "customer_id")
//	private Customer customer;

	@Column(unique = true)
	private int customerId;

	@Column(unique = true)
	private String uniqueId;

	private LocalDateTime timeStamp;
}
