package com.renanCompany.dioBanck.security.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renanCompany.dioBanck.security.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
