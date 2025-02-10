package com.ePay.service;

import org.springframework.stereotype.Service;

import com.ePay.model.Customer;
import com.ePay.model.CustomerSession;
import com.ePay.model.DTO.CustomerDTO;
import com.ePay.model.DTO.CustomerLoginDTO;
import com.ePay.model.DTO.CustomerOtpDTO;

@Service
public interface CustomerService {
	public Customer createCustomer(Customer customer);

	public String customerLogin(CustomerLoginDTO customerDto);

	public String customerLogout(String uniqueId);

	public CustomerSession checkCustomerSession(String UniqueId);

	public CustomerDTO viewCustomerDetails(String UniqueId);

	public String updateCustomerPassword(CustomerLoginDTO customerDto);

	public String checkCustomer(CustomerOtpDTO customerOTP);

	public String checkNotACustomer(CustomerOtpDTO customerOTP);

}
