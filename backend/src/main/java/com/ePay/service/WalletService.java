package com.ePay.service;

import com.ePay.model.Wallet;

public interface WalletService {

	public String updateKYC(String uniqueId);

	public String showBalance(String uniqueId);

	public Wallet addMoneytoWallet(String BankId, Long amount, String uniqueId);

	public Wallet transferToBank(String BankId, Long amount, String uniqueId);

	public Wallet fundTransfer(String sourceMobileNo, String targetMobileNo, Long amount, String uniqueId);

	public Wallet updateWallet(Wallet wallet);
}
