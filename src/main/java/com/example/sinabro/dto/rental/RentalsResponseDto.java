package com.example.sinabro.dto.rental;

import com.example.sinabro.entity.Rental.Rentals;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalsResponseDto {

    private String studentId;

    private String itemDetailName;

    private String content;

    private LocalDate date;

    public static RentalsResponseDto toDto(Rentals rentals) {
        return new RentalsResponseDto(rentals.getUsers().getStudentId(), rentals.getItem().getItemDetailName()
                , rentals.getContents(), rentals.getBorrowDate());
    }
}
