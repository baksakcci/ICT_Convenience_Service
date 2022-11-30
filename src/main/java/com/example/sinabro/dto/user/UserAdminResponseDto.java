package com.example.sinabro.dto.user;

import com.example.sinabro.entity.user.Users;
import com.example.sinabro.entity.user.UsersRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAdminResponseDto {
    private String studentId;
    private String name;
    private String password;

    public static UserAdminResponseDto toDto(Users users) {
        return new UserAdminResponseDto(users.getStudentId(),users.getName(), users.getPassword());
    }
}
