package com.example.sinabro.repository;

import com.example.sinabro.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByStudentId(String studentId);

    Optional<Users> findByName(String name);
}
