package com.ePay.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ePay.exception.BillPaymentException;
import com.ePay.exception.CustomerException;
import com.ePay.exception.WalletException;
import com.ePay.model.BillPayment;
import com.ePay.model.Customer;
import com.ePay.model.CustomerSession;
import com.ePay.model.Transaction;
import com.ePay.model.Wallet;
import com.ePay.repository.BillPaymentDao;
import com.ePay.repository.CustomerDao;
import com.ePay.repository.CustomerSessionDao;
import com.ePay.repository.TransactionDao;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {
	@Autowired
	private BillPaymentDao bDao;

	@Autowired
	private CustomerSessionDao csDao;

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private TransactionDao tDao;

	@Override
	public BillPayment addBillPayment(BillPayment bill, String uniqueId) throws WalletException {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer customer = opt.get();
			Wallet wallet = customer.getWallet();

			if (wallet.getBalance() >= bill.getAmount()) {
				long revisedBalance = wallet.getBalance() - bill.getAmount();
				wallet.setBalance(revisedBalance);
				bill.setPaymentDate(LocalDate.now());
				cDao.save(customer);

				// add transaction
				Transaction transaction = new Transaction();
				transaction.setAmount(bill.getAmount());
				transaction.setDescription(bill.getBillDescription());
				transaction.setTransactionDate(LocalDateTime.now());
				transaction.setTransactionType("Bill Payment");
				transaction.setWallet(wallet);
				tDao.save(transaction);
				bill.setWallet(wallet);
				bDao.save(bill);
				return bill;

			} else {
				throw new WalletException("Insufficient funds in wallet!");
			}

		} else {
			throw new CustomerException("User not logged in!");
		}

	}

	@Override
	public List<BillPayment> viewPaidBills(String uniqueId) throws BillPaymentException {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer customer = opt.get();
			Wallet wallet = customer.getWallet();
			List<BillPayment> bills = bDao.findByWallet(wallet);
			if (bills.size() != 0) {
				return bills;
			} else {
				throw new CustomerException("no bill found for this user");
			}
		} else {
			throw new CustomerException("User not logged in!");
		}
	}

	/*
	 * @Override public BillPayment viewBillPayment(String uid, Integer billId)
	 * throws BillPaymentException { // TODO Auto-generated method stub
	 * CustomerSession customerSession = csd.findByUniqueId(uid);
	 * 
	 * 
	 * Optional<Customer> Opcustomer =
	 * cdao.findById(customerSession.getCustomerId());
	 * 
	 * 
	 * Customer customer = Opcustomer.get() ; BillPayment payment = null;
	 * 
	 * for (BillPayment payment2 : customer.getWallet().getBillPayments()) { if
	 * (payment2.getBillId() == billId) { payment = payment2; break; } }
	 * 
	 * if (payment != null) { return payment; } else throw new
	 * BillPaymentException("invalid credentials");
	 * 
	 * 
	 * return null; }
	 */

	/*
	 * public BillPayment addBillPayment(BillPayment payment , String uid)throws
	 * WalletException{
	 * 
	 * 
	 * CustomerSession customerSession = csd.findByUniqueId(uid);
	 * 
	 * 
	 * Optional<Customer> Opcustomer =
	 * cdao.findById(customerSession.getCustomerId());
	 * 
	 * 
	 * Customer customer = Opcustomer.get() ; long updatedWalletbal =
	 * customer.getWallet().getBalance() - payment.getAmount();
	 * 
	 * if (updatedWalletbal < 0) { throw new
	 * WalletException("Insufficient balance!"); } else {
	 * customer.getWallet().setBalance(updatedWalletbal);
	 * payment.setWallet(customer.getWallet()); //Transaction transaction = new
	 * Transaction(); // transaction.setTransactionDate(LocalDateTime.now()); //
	 * transaction.setAmount(payment.getAmount()); //
	 * transaction.setDescription("Bill is paid"); //
	 * transaction.setTransactionType(payment.getBillType()); //
	 * ts.addTransaction(transaction, uid);
	 * 
	 * return bpd.save(payment);
	 * 
	 * //}
	 * 
	 * }
	 */

//	public BillPayment addBillPayment(BillPayment payment , String uid)throws WalletException{
//		
//		
//		
//		//Transaction transaction = new Transaction();
//	//	transaction.setTransactionDate(LocalDateTime.now());
//	//	transaction.setAmount(payment.getAmount());
//	//	transaction.setDescription("Bill is paid");
//	//	transaction.setTransactionType(payment.getBillType());
//	//	ts.addTransaction(transaction, uid);
//
//		return bpd.save(payment);
//
//	//}
//
//}

}
