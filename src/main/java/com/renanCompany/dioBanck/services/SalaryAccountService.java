package com.renanCompany.dioBanck.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.renanCompany.dioBanck.entities.Account;

import jakarta.transaction.Transactional;

@Service
public class SalaryAccountService {
	Account account = new Account();
	
	@Transactional(rollbackOn = Exception.class)
	public void withdraw(BigDecimal value) {
		if (value.compareTo(account.getBalance()) <= 0) {
			account.setBalance(account.getBalance().subtract(value));
			System.out.println("withdraw succeeded!");
		}
		throw new RuntimeException("withdraw not succeeded! no balance enough.");
	}

}
