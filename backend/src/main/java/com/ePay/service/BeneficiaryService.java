package com.ePay.service;

import java.util.List;

import com.ePay.exception.BeneficiaryException;
import com.ePay.exception.CustomerException;
import com.ePay.model.Beneficiary;

public interface BeneficiaryService {
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String UniqueID)
			throws BeneficiaryException, CustomerException;

	public List<Beneficiary> viewBeneficiaries(String UniqueID) throws CustomerException, BeneficiaryException;

	public Beneficiary deleteBeneficiary(String UniqueID, Integer bId) throws CustomerException, BeneficiaryException;

}
