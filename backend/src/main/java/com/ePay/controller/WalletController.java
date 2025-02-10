package com.ePay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ePay.model.Wallet;
import com.ePay.service.AccountService;
import com.ePay.service.WalletService;

@RestController
@CrossOrigin(origins = "*")
public class WalletController {

	@Autowired
	@SuppressWarnings(value = { "unused" })
	private AccountService accountServices;
	@Autowired
	private WalletService walletService;

	@PutMapping("Kyc/{uniqueId}")
	public ResponseEntity<String> updateKYCStatus(@PathVariable String uniqueId) {
		String msg = walletService.updateKYC(uniqueId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/balance/{uniqueId}")
	public ResponseEntity<String> showBalance(@PathVariable String uniqueId) {
		String balance = walletService.showBalance(uniqueId);
		return new ResponseEntity<String>(balance, HttpStatus.OK);
	}

	@PostMapping("/addMoney/{BankId}/{amount}/{uniqueId}")
	public ResponseEntity<Wallet> addMoneytoWallet(@PathVariable String BankId, @PathVariable Long amount,
			@PathVariable String uniqueId) {
		Wallet wallet = walletService.addMoneytoWallet(BankId, amount, uniqueId);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

	@PostMapping("/bankTrf/{BankId}/{amount}/{uniqueId}")
	public ResponseEntity<Wallet> transferToBank(@PathVariable String BankId, @PathVariable Long amount,
			@PathVariable String uniqueId) {
		Wallet wallet = walletService.transferToBank(BankId, amount, uniqueId);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

	@PostMapping("/sendMoney/{sourceMobileNo}/{targetMobileNo}/{amount}/{uniqueId}")
	public ResponseEntity<Wallet> transferToBank(@PathVariable String sourceMobileNo,
			@PathVariable String targetMobileNo, @PathVariable Long amount, @PathVariable String uniqueId) {
		Wallet wallet = walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount, uniqueId);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

}
