package com.example.sinabro.service;

import com.example.sinabro.dto.user.UserRequestDto;
import com.example.sinabro.dto.user.UserResponseDto;
import com.example.sinabro.entity.member.User;
import com.example.sinabro.entity.member.UserRole;
import com.example.sinabro.exception.UserNotFoundException;
import com.example.sinabro.exception.SignupViolationException;
import com.example.sinabro.exception.UserPasswordNotEqualException;
import com.example.sinabro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto urd) {
        if(!userRepository.findByStudentId(urd.getStudentId()).isEmpty()) {
            throw new SignupViolationException();
        }

        User user = new User(urd.getStudentId(), urd.getPassword());
        if("admin".equals(urd.getStudentId())) {
            user.setUserRole(UserRole.ADMIN);
        } else {
            user.setUserRole(UserRole.USER);
        }
        userRepository.save(user);
        return UserResponseDto.toDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUser(String id) {
        User user = userRepository.findByStudentId(id).orElseThrow(UserNotFoundException::new);
        return UserResponseDto.toDto(user);
    }

    @Transactional
    public UserResponseDto loginUser(UserRequestDto urd) {
        User u = new User(urd.getStudentId(), urd.getPassword());
        User user = userRepository.findByStudentId(urd.getStudentId()).orElseThrow(UserNotFoundException::new);

        if(!u.getPassword().equals(user.getPassword())) {
            throw new UserPasswordNotEqualException();
        }
        return UserResponseDto.toDto(user);
    }

    @Transactional
    public void updateMember(UserRequestDto mem, Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.editMember(mem.getStudentId(), mem.getPassword());
        userRepository.save(user);
    }

    @Transactional
    public void deleteMember(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findMemberAll() {
        List<User> items = userRepository.findAll();
        List<UserResponseDto> result = new ArrayList<>();
        for (User user : items) {
            result.add(UserResponseDto.toDto(user));
        }
        return result;
    }


}
