package com.ePay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ePay.model.CustomerSession;

@Repository
public interface CustomerSessionDao extends JpaRepository<CustomerSession, Integer> {

	public CustomerSession findByUniqueId(String UniqueId);

	public CustomerSession findByCustomerId(Integer customerId);
}
