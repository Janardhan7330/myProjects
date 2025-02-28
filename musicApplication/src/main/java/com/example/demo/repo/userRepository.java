package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface userRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);
}
