package com.ePay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ePay.model.BillPayment;
import com.ePay.model.Wallet;

public interface BillPaymentDao extends JpaRepository<BillPayment, Integer> {
	List<BillPayment> findByWallet(Wallet wallet);
}
