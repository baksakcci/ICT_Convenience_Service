package com.example.sinabro.dto.user;

import com.example.sinabro.entity.user.Users;
import com.example.sinabro.entity.user.UsersRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersLoginResponseDto {
    private String studentId;
    private String password;
    private UsersRole userRole;

    public static UsersLoginResponseDto toDto(Users users) {
        return new UsersLoginResponseDto(users.getStudentId(), users.getPassword(), users.getUsersRole());
    }
}
