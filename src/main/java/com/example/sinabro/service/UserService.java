package com.example.sinabro.service;

import com.example.sinabro.dto.union.UnionResponseDto;
import com.example.sinabro.dto.user.UsersLoginRequestDto;
import com.example.sinabro.dto.user.UsersLoginResponseDto;
import com.example.sinabro.entity.union.Union;
import com.example.sinabro.entity.user.Users;
import com.example.sinabro.exception.UserNotFoundException;
import com.example.sinabro.exception.UserPasswordNotEqualException;
import com.example.sinabro.repository.UnionRepository;
import com.example.sinabro.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final UnionRepository unionRepository;

    @Transactional
    public UsersLoginResponseDto loginUser(UsersLoginRequestDto urd) {
        Users u = new Users();
        u.loginUser(urd.getStudentId(), urd.getPassword());
        Users users = usersRepository.findByStudentId(urd.getStudentId()).orElseThrow(UserNotFoundException::new);

        if(!u.getPassword().equals(users.getPassword())) {
            throw new UserPasswordNotEqualException();
        }
        return UsersLoginResponseDto.toDto(users);
    }

    @Transactional(readOnly = true)
    public UnionResponseDto getOpen() {
        Union union = unionRepository.findById(1L).orElseThrow();

        UnionResponseDto unionResponseDto = new UnionResponseDto(union);
        return unionResponseDto;
    }
}
