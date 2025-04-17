package com.example.auth.repository;

import com.example.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsernameOrEmail(String username, String email);
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
} 