package com.example.sinabro.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersLoginRequestDto {
    @NotNull(message = "학번을 입력하세요")
    private String studentId;

    @NotBlank(message = "올바른 비밀번호를 입력하세요")
    private String password;
}