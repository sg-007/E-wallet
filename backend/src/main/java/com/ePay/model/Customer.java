package com.ePay.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@NotBlank
	@Size(min = 3, max = 20, message = "Customer name should be min 3 and max 20 character length")
	private String firstName;

	@NotBlank
	@Size(min = 3, max = 20, message = "Customer name should be min 3 and max 20 character length")
	private String lastName;

	@NotBlank
	@Size(min = 10, max = 10, message = "Mobile number should be 10 digits")
	private String mobileNumber;

	@NotBlank
	@Size(min = 8, max = 15, message = "Password should be min 8 and max 15 character length")
	private String password;

	private LocalDate dob;

	@NotBlank
	private String gender;

	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;

	// Optionally re-enable session mapping if needed
	// @OneToOne(mappedBy = "customer")
	// private CustomerSession customerSession;
}
	//{"fistName":"Sandeep", "lastName": "Maheshwari","mobileNumber":"8860578503", "gender": "Male", "dob": "1999-12-12","wallet":{"walletId": mobileNumber+@epay, "balance":1, "kycStatus": false},"password":"123456789"}

	//{
	//"accountNo":"123456789",
	//"ifscCode":"SBIN0788",
	//"bankName":"SBI",
	//"balance":"20000"
	//}`