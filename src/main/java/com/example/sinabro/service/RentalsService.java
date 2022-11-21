package com.example.sinabro.service;

import com.example.sinabro.dto.rental.RentalsResponseDto;
import com.example.sinabro.entity.Rental.Rentals;
import com.example.sinabro.exception.UserNotFoundException;
import com.example.sinabro.repository.RentalsRepository;
import com.example.sinabro.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalsService {

    private final RentalsRepository rentalsRepository;
    private final UsersRepository usersRepository;

    /*
    [[ Rental ]]
    자신이 대여했던 대여 내역만 보여줌
     */
    @Transactional(readOnly = true)
    public List<RentalsResponseDto> findAll(String studentId) {
        usersRepository.findByStudentId(studentId).orElseThrow(UserNotFoundException::new);

        List<Rentals> rentals = rentalsRepository.findAllByUsers_StudentId(studentId);
        List<RentalsResponseDto> rentalsResponseDtoList = new ArrayList<>();
        for(Rentals n : rentals) {
            RentalsResponseDto rentalsResponseDto = RentalsResponseDto.toDto(n);
            rentalsResponseDtoList.add(rentalsResponseDto);
        }
        return rentalsResponseDtoList;
    }
}
