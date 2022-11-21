package com.example.sinabro.dto.user;

import com.example.sinabro.entity.user.Users;
import com.example.sinabro.entity.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {
    private String studentId;
    private String password;
    private UserRole userRole;

    public static UserResponseDto toDto(Users users) {
        return new UserResponseDto(users.getStudentId(), users.getPassword(), users.getUserRole());
    }
}
