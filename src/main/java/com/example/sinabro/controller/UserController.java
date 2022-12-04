package com.example.sinabro.controller;

import com.example.sinabro.dto.user.UsersLoginRequestDto;
import com.example.sinabro.response.Response;
import com.example.sinabro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /*
    [[ User ]]
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public Response login(@Valid @RequestBody UsersLoginRequestDto usersLoginRequestDto) {
        return Response.success(userService.loginUser(usersLoginRequestDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/union")
    public Response getUnion() {
        return Response.success(userService.getOpen());
    }
}
