package com.ePay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ePay.exception.BillPaymentException;
import com.ePay.model.BillPayment;
import com.ePay.service.BillPaymentService;

@RestController
@CrossOrigin(origins = "*")
public class PaymentController {

	@Autowired
	private BillPaymentService billPaymentService;

	@PostMapping("/billpayment/{uniqueid}")
	public ResponseEntity<BillPayment> addBillpaymentHandler(@RequestBody BillPayment billPayment,
			@PathVariable("uniqueid") String uniqueid) {
		BillPayment savedBillPayment = billPaymentService.addBillPayment(billPayment, uniqueid);
		return new ResponseEntity<BillPayment>(savedBillPayment, HttpStatus.CREATED);
	}

	@GetMapping("/paidBills/{uniqueid}")
	public ResponseEntity<List<BillPayment>> getBillPaymentByBillIdHandler(@PathVariable String uniqueid)
			throws BillPaymentException {

		List<BillPayment> bills = billPaymentService.viewPaidBills(uniqueid);
		return new ResponseEntity<List<BillPayment>>(bills, HttpStatus.OK);
	}
}
