package com.ePay.model;

import java.time.LocalDate;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Data
public class BillPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billId;
	private String billType;
	private Long amount;
	private LocalDate paymentDate;
	private String billDescription;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;

	// {"billType":"payment","amount":"500","paymentDate":"2018-03-29","billDescription":"Electricity
	// bill payment"
	// }

}
