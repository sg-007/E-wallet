package com.ePay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ePay.model.BankAccount;

public interface accountDao extends JpaRepository<BankAccount, Integer> {

	BankAccount findByAccountNo(String accountNo);

}
