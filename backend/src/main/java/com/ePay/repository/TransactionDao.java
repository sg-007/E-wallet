package com.ePay.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ePay.model.Transaction;
import com.ePay.model.Wallet;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByTransactionDateBetween(LocalDateTime from, LocalDateTime to);

	List<Transaction> findByTransactionType(String type);

	List<Transaction> findByWallet(Wallet wallet);

}
