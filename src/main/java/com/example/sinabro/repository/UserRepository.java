package com.example.sinabro.repository;

import com.example.sinabro.entity.member.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByStudentId(String studentId);
}
