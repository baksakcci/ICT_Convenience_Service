package com.example.sinabro.dto.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalDeleteRequestDto {
    @NotNull(message = "어떤 유저인지 입력하세요")
    private String studentId;
}
