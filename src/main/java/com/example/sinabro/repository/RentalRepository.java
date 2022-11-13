package com.example.sinabro.repository;

import com.example.sinabro.entity.rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}
