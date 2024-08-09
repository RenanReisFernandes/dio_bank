package com.renanCompany.dioBanck.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renanCompany.dioBanck.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	Optional<Client> findByCpf(String cpf);

}
