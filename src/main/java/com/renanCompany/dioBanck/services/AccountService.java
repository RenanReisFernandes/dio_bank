package com.renanCompany.dioBanck.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renanCompany.dioBanck.entities.Account;
import com.renanCompany.dioBanck.repositories.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account save(Account account) {
		return accountRepository.save(account);
	}

	public List<Account> findAll() {
		List<Account> existentAccounts = accountRepository.findAll();
		if (existentAccounts == null || existentAccounts.isEmpty()) {
			throw new NoSuchElementException("No account found!");
		}
		return existentAccounts;
	}

	public Optional<Account> findById(Long id) {
		Optional<Account> optAccount = accountRepository.findById(id);
		if (optAccount.isEmpty()) {
			throw new NoSuchElementException("No account found!");
		}
		return optAccount;
	}

	public Optional<Account> findByAccountNumber(Long accountNumber) {
		Optional<Account> optAcount = accountRepository.findByaccountNumber(accountNumber);
		if (optAcount.isEmpty()) {
			throw new NoSuchElementException("Account not found!");
		}
		return optAcount;
	}
	
	public void delete(Long id) {
		accountRepository.deleteById(id);
	}
	
}
