package com.ePay.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
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
