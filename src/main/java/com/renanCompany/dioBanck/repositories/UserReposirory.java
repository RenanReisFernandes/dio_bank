package com.renanCompany.dioBanck.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renanCompany.dioBanck.entities.User;

@Repository
public interface UserReposirory extends JpaRepository<User, UUID> {

}
