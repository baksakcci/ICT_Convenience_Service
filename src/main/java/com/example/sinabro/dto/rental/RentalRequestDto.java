package com.example.sinabro.dto.rental;

import com.example.sinabro.entity.rental.Rented;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalRequestDto {

    @NotNull(message = "대여상태를 입력해야 합니다.")
    private Rented isRental;

}
