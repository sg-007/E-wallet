package com.ePay.service;

import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ePay.exception.BeneficiaryException;
import com.ePay.exception.CustomerException;
import com.ePay.model.Beneficiary;
import com.ePay.model.Customer;
import com.ePay.model.CustomerSession;
import com.ePay.repository.BeneficiaryDAO;
import com.ePay.repository.CustomerDao;
import com.ePay.repository.CustomerSessionDao;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	@Autowired
	@SuppressWarnings(value = { "unused" })
	private CustomerSessionDao sessionDAO;

	@Autowired
	private BeneficiaryDAO beneficiaryDAO;

	@Autowired
	private CustomerSessionDao csDao;

	@Autowired
	private CustomerDao cDao;

	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary, String UniqueID)
			throws BeneficiaryException, CustomerException {

		CustomerSession cSession = csDao.findByUniqueId(UniqueID);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer existingCustomer = opt.get();
			System.out.println(beneficiary.toString());
			beneficiary.setCustomer(existingCustomer);
			return beneficiaryDAO.save(beneficiary);
		}
		{
			throw new CustomerException("customer is not logged in with given sourceMobileNo");
		}

	}

	@Override
	public List<Beneficiary> viewBeneficiaries(String UniqueID) throws CustomerException, BeneficiaryException {
		CustomerSession cSession = csDao.findByUniqueId(UniqueID);
		if (cSession != null) {
			Optional<Customer> opt = cDao.findById(cSession.getCustomerId());
			Customer existingCustomer = opt.get();
			List<Beneficiary> beneficiaries = beneficiaryDAO.findByCustomer(existingCustomer);

			if (beneficiaries != null) {
				return beneficiaries;

			} else {
				throw new BeneficiaryException("No beneficiary yet");
			}

		}
		{
			throw new CustomerException("customer is not logged in with given source mobile no.");
		}

	}

	@Override
	public Beneficiary deleteBeneficiary(String UniqueID, Integer bId) throws CustomerException, BeneficiaryException {
		CustomerSession cSession = csDao.findByUniqueId(UniqueID);
		if (cSession != null) {
			Optional<Beneficiary> opt = beneficiaryDAO.findById(bId);
			if (opt.isPresent()) {
				Beneficiary existingBeneficiary = opt.get();
				beneficiaryDAO.delete(existingBeneficiary);
				return existingBeneficiary;
			} else {
				throw new BeneficiaryException("no beneficiary available with the given id");
			}

		}
		{
			throw new CustomerException("customer is not logged in with given source mobile no.");
		}

	}

}
