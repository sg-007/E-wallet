package com.ePay.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ePay.exception.BillPaymentException;
import com.ePay.exception.WalletException;
import com.ePay.model.BillPayment;

@Service
public interface BillPaymentService {
	// add bill -- pay bill -- add paid bills to database
	public BillPayment addBillPayment(BillPayment bill, String uuid) throws WalletException;

	public List<BillPayment> viewPaidBills(String uid) throws BillPaymentException;

}