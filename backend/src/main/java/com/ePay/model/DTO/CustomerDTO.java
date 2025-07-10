package com.ePay.model.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class CustomerDTO {

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String gender;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	private WalletDTO wallet;
}
