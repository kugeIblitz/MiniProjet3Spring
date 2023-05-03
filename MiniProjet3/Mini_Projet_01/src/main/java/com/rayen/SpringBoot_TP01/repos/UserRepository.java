package com.rayen.SpringBoot_TP01.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rayen.SpringBoot_TP01.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername (String username);
}