package com.ePay.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ePay.model.Customer;
import com.ePay.model.DTO.CustomerDTO;
import com.ePay.model.DTO.CustomerLoginDTO;
import com.ePay.model.DTO.CustomerOtpDTO;
import com.ePay.service.CustomerService;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
	@Autowired
	private CustomerService cService;

	// get otp if customer
	@PostMapping("/forgotPassword")
	public ResponseEntity<String> otpHandler(@Valid @RequestBody CustomerOtpDTO customerOTP) {
		return new ResponseEntity<String>(cService.checkCustomer(customerOTP), HttpStatus.ACCEPTED);
	}

	// get otp if new customer
	@PostMapping("/signUp")
	public ResponseEntity<String> newCustomerOtpHandler(@Valid @RequestBody CustomerOtpDTO customerOTP) {
		return new ResponseEntity<String>(cService.checkNotACustomer(customerOTP), HttpStatus.ACCEPTED);
	}

	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomerHandler(@Valid @RequestBody Customer customer) {
		Customer newCustomer = cService.createCustomer(customer);
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.ACCEPTED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginCustomerHandler(@Valid @RequestBody CustomerLoginDTO customerDTO) {
		return new ResponseEntity<String>(cService.customerLogin(customerDTO), HttpStatus.ACCEPTED);
	}

	@PostMapping("/customer/updatePassword")
	public ResponseEntity<String> updateCustomerPasswordHandler(@Valid @RequestBody CustomerLoginDTO customerDTO) {
		String message = cService.updateCustomerPassword(customerDTO);
		HttpStatus status = message.equals("Password changed successfully.") ? HttpStatus.ACCEPTED
				: HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(message, status);
	}

	@PatchMapping("/logout/{uniqueId}")
	public ResponseEntity<String> logoutCustomerHandler(@PathVariable String uniqueId) {

		return new ResponseEntity<String>(cService.customerLogout(uniqueId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/customer/{uniqueId}")
	public ResponseEntity<CustomerDTO> viewCustomerDetailsHandler(@PathVariable("uniqueId") String uniqueId) {
		CustomerDTO newCustomer = cService.viewCustomerDetails(uniqueId);
		System.out.println(newCustomer.toString());
		return new ResponseEntity<CustomerDTO>(newCustomer, HttpStatus.ACCEPTED);
	}

}
