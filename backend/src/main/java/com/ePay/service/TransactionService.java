package com.ePay.service;

import java.util.List;

import com.ePay.model.Transaction;

public interface TransactionService {
	public Transaction addTransaction12(Transaction trans, String uniqueId);

	// {"transactionType":"Payment","LocalDateTime":"1999-06-21","amount":"10000"
	// ,"description":"wallet transfer"}

	public List<Transaction> viewAllTransaction(String uniqueId);

	// public List<Transaction> viewTransactionByDate(LocalDateTime from,
	// LocalDateTime to);

	public List<Transaction> viewAllTransactionByType(String type, String uniqueId);
}
