package com.ePay.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WalletDTO {

	private String walletId;
	private long balance;

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public boolean isKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(boolean kycStatus) {
		this.kycStatus = kycStatus;
	}

	private boolean kycStatus;

}