package com.example.sinabro.service;

import com.example.sinabro.dto.user.UsersRequestDto;
import com.example.sinabro.dto.user.UsersResponseDto;
import com.example.sinabro.entity.user.Users;
import com.example.sinabro.exception.UserNotFoundException;
import com.example.sinabro.exception.UserPasswordNotEqualException;
import com.example.sinabro.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;

    @Transactional
    public UsersResponseDto loginUser(UsersRequestDto urd) {
        Users u = new Users(urd.getStudentId(), urd.getPassword());
        Users users = usersRepository.findByStudentId(urd.getStudentId()).orElseThrow(UserNotFoundException::new);

        if(!u.getPassword().equals(users.getPassword())) {
            throw new UserPasswordNotEqualException();
        }
        return UsersResponseDto.toDto(users);
    }
}
