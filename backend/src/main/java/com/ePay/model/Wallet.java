package com.ePay.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Data
@Entity
public class Wallet {

	@Id
	private String walletId;

	private boolean kycStatus = false;
	private Long balance;

	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
	List<BankAccount> banks = new ArrayList<>();
}
