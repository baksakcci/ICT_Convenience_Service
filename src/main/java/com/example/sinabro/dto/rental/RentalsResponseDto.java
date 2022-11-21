package com.example.sinabro.dto.rental;

import com.example.sinabro.entity.Rental.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalResponseDto {

    private String studentId;

    private String itemDetailName;

    private String content;

    private LocalDate date;

    public static RentalResponseDto toDto(Rental rental) {
        return new RentalResponseDto(rental.getUsers().getStudentId(), rental.getItem().getItemDetailName()
                , rental.getContent(), rental.getCreateDate());
    }
}
