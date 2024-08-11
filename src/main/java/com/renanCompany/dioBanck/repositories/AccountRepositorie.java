package com.renanCompany.dioBanck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renanCompany.dioBanck.entities.Account;

@Repository
public interface AccountRepositorie extends JpaRepository<Account, Long> {
	

}
