package com.ePay.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Wallet {
	@Id
	private String walletId;

	private boolean kycStatus = false;

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public boolean isKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(boolean kycStatus) {
		this.kycStatus = kycStatus;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public List<BankAccount> getBanks() {
		return banks;
	}

	public void setBanks(List<BankAccount> banks) {
		this.banks = banks;
	}

	private Long balance;

	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
	List<BankAccount> banks = new ArrayList<>();

}
