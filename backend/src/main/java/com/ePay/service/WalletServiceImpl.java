package com.ePay.service;

import java.time.LocalDateTime;
//import java.math.BigDecimal;
//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ePay.exception.CustomerException;
import com.ePay.model.BankAccount;
import com.ePay.model.Customer;
import com.ePay.model.CustomerSession;
import com.ePay.model.Transaction;
import com.ePay.model.Wallet;
import com.ePay.repository.CustomerDao;
import com.ePay.repository.CustomerSessionDao;
import com.ePay.repository.TransactionDao;
import com.ePay.repository.WalletDao;
import com.ePay.repository.accountDao;

@Service
public class WalletServiceImpl implements WalletService {

	@SuppressWarnings("unused")
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private accountDao aDao;

	@Autowired
	private WalletDao wDao;

	@Autowired
	private CustomerDao cDao;

	@Autowired
	private TransactionDao tDao;

	@Autowired
	private CustomerSessionDao csDao;

	@SuppressWarnings("unused")
	@Autowired
	private CustomerService csSession;

	@Override
	public String showBalance(String uniqueId) {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer customer = opt.get();
			return "customer Balance is : " + customer.getWallet().getBalance();

		} else {
			throw new CustomerException("User not logged in with this number!");
		}

	}

	@Override
	public Wallet addMoneytoWallet(String BankId, Long amount, String uniqueId) {

		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer customer = opt.get();

			Optional<BankAccount> optbank = Optional.ofNullable(aDao.findByAccountNo(BankId));
			BankAccount bank = optbank.get();

			// transaction
			Wallet wallet = customer.getWallet();

			if (bank.getBalance() >= amount) {

				long revisedwalletbalance = wallet.getBalance() + amount;
				long revisedBankBalance = bank.getBalance() - amount;

				bank.setBalance(revisedBankBalance);
				wallet.setBalance(revisedwalletbalance);

				Transaction transaction = new Transaction();
				transaction.setAmount(amount);
				transaction.setDescription("Money Received from Bank XXXX" + bank.getAccountNo().substring(8));
				transaction.setReceiversWalletId(wallet.getWalletId());
				transaction.setTransactionDate(LocalDateTime.now());
				transaction.setTransactionType("Received Money");
				transaction.setWallet(wallet);
				tDao.save(transaction);

				cDao.save(customer);
				return customer.getWallet();

			} else {
				throw new CustomerException("Insufficient bank balance");
			}

		} else {
			throw new CustomerException("User not logged in!");
		}

	}

	@Override
	public Wallet transferToBank(String BankId, Long amount, String uniqueId) {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer customer = opt.get();

			Optional<BankAccount> optbank = Optional.ofNullable(aDao.findByAccountNo(BankId));
			BankAccount bank = optbank.get();

			// transaction
			Wallet wallet = customer.getWallet();

			if (wallet.getBalance() >= amount) {

				long revisedwalletbalance = wallet.getBalance() - amount;
				long revisedBankBalance = bank.getBalance() + amount;

				bank.setBalance(revisedBankBalance);
				wallet.setBalance(revisedwalletbalance);

				cDao.save(customer);
				return customer.getWallet();

			} else {
				throw new CustomerException("Insufficient balance in wallet");
			}

		} else {
			throw new CustomerException("User not logged in!");
		}
	}

	// wallet to wallet
	@Override
	public Wallet fundTransfer(String sourceMobileNo, String targetMobileNo, Long amount, String uniqueId) {
		if (!sourceMobileNo.equals(targetMobileNo)) {
			CustomerSession cSession = csDao.findByUniqueId(uniqueId);
			if (cSession != null) {
				Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
				Customer transferor = opt.get();

				if (transferor.getMobileNumber().equals(sourceMobileNo)) {
					Customer transferee = cDao.findByMobileNumber(targetMobileNo);

					// check balance
					if (transferor.getWallet().getBalance() >= amount) {

						Long revisedBankBalanceROR = transferor.getWallet().getBalance() - amount;
						Long revisedBankBalanceREE = transferee.getWallet().getBalance() + amount;
						transferor.getWallet().setBalance(revisedBankBalanceROR);
						transferee.getWallet().setBalance(revisedBankBalanceREE);

						// add transaction
                        Transaction transaction = new Transaction();
						transaction.setAmount(amount);
						transaction.setDescription("Money sent to " + transferee.getWallet().getWalletId());
						transaction.setReceiversWalletId(transferee.getWallet().getWalletId());
						transaction.setTransactionDate(LocalDateTime.now());
						transaction.setTransactionType("Send Money");
						transaction.setWallet(transferor.getWallet());
						tDao.save(transaction);

						cDao.save(transferee);
						cDao.save(transferor);
						return transferor.getWallet();

					} else {
						throw new CustomerException("Insufficient wallet balance!");
					}

				} else {
					throw new CustomerException("customer is not logged in with given sourceMobileNo");
				}

			} else {
				throw new CustomerException("User not logged in!");
			}
		} else {
			throw new CustomerException("sourceMobileNo must not be same as targetMobileNo");
		}

	}

	@Override
	public Wallet updateWallet(Wallet wallet) {
		return wDao.save(wallet);
	}

	@Override
	public String updateKYC(String uniqueId) {
		CustomerSession cSession = csDao.findByUniqueId(uniqueId);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer customer = opt.get();
			Wallet wallet = customer.getWallet();
			wallet.setKycStatus(true);
			wDao.save(wallet);
			cDao.save(customer);
			return "KYC Done!";
		} else {
			return "KYC failed";
		}
	}

}
