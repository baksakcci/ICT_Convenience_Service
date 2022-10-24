package com.example.sinabro.dto.user;

import com.example.sinabro.entity.member.User;
import com.example.sinabro.entity.member.UserRole;
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

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getStudentId(), user.getPassword(), user.getUserRole());
    }
}
