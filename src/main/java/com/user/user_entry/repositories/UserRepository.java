package com.user.user_entry.repositories;

import java.util.List;

import com.user.user_entry.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users WHERE token = ?1", nativeQuery = true)
    List<User> findAllByToken(String token);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    List<User> findAllByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE email = ?1 and password = ?2", nativeQuery = true)
    List<User> findAllByEmailAndPassword(String email, String password);
}