package com.example.sinabro.service;

import com.example.sinabro.dto.rental.RentalResponseDto;
import com.example.sinabro.entity.Rental.Rental;
import com.example.sinabro.exception.UserNotFoundException;
import com.example.sinabro.repository.NoticeRepository;
import com.example.sinabro.repository.RentalRepository;
import com.example.sinabro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    /*
    [[ Rental ]]
    자신이 대여했던 대여 내역만 보여줌
     */
    @Transactional(readOnly = true)
    public List<RentalResponseDto> findAll(String studentId) {
        userRepository.findByStudentId(studentId).orElseThrow(UserNotFoundException::new);

        List<Rental> rentals = rentalRepository.findAllByUsers_StudentId(studentId);
        List<RentalResponseDto> rentalResponseDtoList = new ArrayList<>();
        for(Rental n : rentals) {
            RentalResponseDto rentalResponseDto = RentalResponseDto.toDto(n);
            rentalResponseDtoList.add(rentalResponseDto);
        }
        return rentalResponseDtoList;
    }
}
