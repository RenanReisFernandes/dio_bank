package com.renanCompany.dioBanck.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.renanCompany.dioBanck.entities.Account;

import jakarta.transaction.Transactional;

@Service
public class CheckingAccountService {

	Account account = new Account();

	@Transactional(rollbackOn = Exception.class)
	public void deposit(BigDecimal value) {
		if (value.compareTo(BigDecimal.ZERO) > 0) {
			account.setBalance(account.getBalance().add(value));
			System.out.println("Deposit succeeded!");
		}
		throw new RuntimeException("Deposit not succeeded! Value must be greater than zero.");
	}

	@Transactional(rollbackOn = Exception.class)
	public void withdraw(BigDecimal value) {
		if (value.compareTo(account.getBalance()) <= 0) {
			account.setBalance(account.getBalance().subtract(value));
			System.out.println("withdraw succeeded!");
		}
		throw new RuntimeException("withdraw not succeeded! no balance enough.");
	}

	@Transactional
	public void transfer(Account payee, BigDecimal value) {
		if (value.compareTo(BigDecimal.ZERO) > 0 && account.getBalance().compareTo(value) >= 0) {
			account.setBalance(account.getBalance().subtract(value));

			payee.setBalance(payee.getBalance().add(value));

			System.out.println("Transfer succeeded!");
		} else {
			throw new RuntimeException("Transfer not succeeded! No balance enough.");

		}
	}

}
