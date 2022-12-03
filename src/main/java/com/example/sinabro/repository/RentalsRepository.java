package com.example.sinabro.repository;

import com.example.sinabro.entity.Rental.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalsRepository extends JpaRepository<Rentals, Long> {

    List<Rentals> findByContentsContaining(String keyword);
    List<Rentals> findAllByUsers_StudentId(String studentId);
    Rentals findTopByUsers_StudentIdOrderByBorrowDateDesc(String studentId);
}
