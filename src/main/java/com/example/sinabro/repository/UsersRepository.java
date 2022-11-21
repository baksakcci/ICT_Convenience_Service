package com.example.sinabro.repository;

import com.example.sinabro.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByStudentId(String studentId);
}
