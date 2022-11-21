package com.example.sinabro.dto.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalsRequestDto {
    @NotNull(message = "타이틀을 입력하세요")
    private String content;

    @NotNull(message = "어떤 아이템을 대여하였는지 입력하세요")
    private String itemDetailName;

    @NotNull(message = "어떤 유저인지 입력하세요")
    private String studentId;
}
