package com.renanCompany.dioBanck.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renanCompany.dioBanck.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByaccountNumber(Long accountNumber);
	
}
