package com.example.sinabro.controller;

import com.example.sinabro.dto.user.UserRequestDto;
import com.example.sinabro.response.Response;
import com.example.sinabro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    /*
    [[ User ]]
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/users/login")
    public Response login(@Valid @RequestBody UserRequestDto userRequestDto) {
        return Response.success(userService.loginUser(userRequestDto));
    }


    /*
    [[ Admin ]]
    회원은 관리자만 추가할 수 있게 만듬(회원가입)
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/signup")
    public Response signup(@Valid @RequestBody UserRequestDto userRequestDto){
        return Response.success(userService.createUser(userRequestDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{studentId}")
    public Response getUser(@PathVariable("studentId") String studentId) {
        return Response.success(userService.findUser(studentId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public Response getUserAll() {
        return Response.success(userService.findMemberAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{id}")
    public Response updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserRequestDto userRequestDto) {
        userService.updateMember(userRequestDto, id);
        return Response.success("회원 수정 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{id}")
    public Response deleteUser(@PathVariable("id") Long id) {
        userService.deleteMember(id);
        return Response.success("회원 삭제 완료");
    }




}
