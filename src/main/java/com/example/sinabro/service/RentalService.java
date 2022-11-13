package com.example.sinabro.service;

import com.example.sinabro.dto.rental.RentalResponseDto;
import com.example.sinabro.entity.rental.Rental;
import com.example.sinabro.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    @Transactional(readOnly = true)
    public List<RentalResponseDto> getRentalAll() {
        List<Rental> rentals = rentalRepository.findAll();
        ArrayList<RentalResponseDto> dtos = new ArrayList<>();
        for(Rental r : rentals) {
            RentalResponseDto dto = RentalResponseDto.toDto(r);
            dtos.add(dto);
        }
        return dtos;
    }

}
