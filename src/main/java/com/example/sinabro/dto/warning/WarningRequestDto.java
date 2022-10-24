package com.example.sinabro.dto.warning;

import com.example.sinabro.entity.item.Used;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarningRequestDto {
    @NotBlank(message = "어떤 경고인지 입력하세요")
    private String warning;

    @NotBlank(message = "해당 경고의 세부 메세지를 입력하세요")
    private String message;

}
