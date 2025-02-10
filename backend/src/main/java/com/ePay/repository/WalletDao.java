package com.ePay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ePay.model.Wallet;

public interface WalletDao extends JpaRepository<Wallet, String> {

}
