package com.example.sinabro.dto.union;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnionRequestDto {
    @NotNull(message = "학생회실 개폐 여부를 open or close로 나타내주세요")
    public boolean isOpen;
}
