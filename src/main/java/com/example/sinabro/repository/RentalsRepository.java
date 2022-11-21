package com.example.sinabro.repository;

import com.example.sinabro.entity.Rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByContentContaining(String keyword);
    List<Rental> findAllByUsers_StudentId(String StudentId);
}
