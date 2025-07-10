package com.ePay.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Data

public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;

	private Integer bankTransactionId;
	private String transactionType;
	private LocalDateTime transactionDate;
	private Long amount;
	private String description;

	private String receiversWalletId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "walletId")
	private Wallet wallet;

}
