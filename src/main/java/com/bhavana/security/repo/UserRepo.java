package com.bhavana.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhavana.security.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
