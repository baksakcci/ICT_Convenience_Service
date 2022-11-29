package com.example.sinabro.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemRentalRequestDto {

    @NotBlank(message = "대여할 학생의 학번을 입력하세요")
    private String studentId;

    @NotBlank(message = "대여할 물품을 입력하세요")
    private String itemDetailName;
}
