package com.example.sinabro.advice;

import com.example.sinabro.exception.*;
import com.example.sinabro.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(SignupViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Response SignupViolationExceptionAdvice(SignupViolationException e) {
        return Response.failure(406, "이미 등록된 사용자입니다.");
    }

    @ExceptionHandler(UserPasswordNotEqualException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Response UserPasswordNotEqualExceptionAdvice(UserPasswordNotEqualException e) {
        return Response.failure(406, "비밀번호가 틀립니다.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response UserNotFoundExceptionAdvice(UserNotFoundException e) {
        return Response.failure(404, "회원정보를 찾을 수 없습니다.");
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response ItemNotFoundExceptionAdvice(ItemNotFoundException e) {
        return Response.failure(404, "물품을 찾을 수 없습니다.");
    }

    @ExceptionHandler(WarningNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response WarningNotFoundExceptionAdvice(WarningNotFoundException e) {
        return Response.failure(404, "회원님의 제재내역을 찾을 수 없습니다.");
    }

    @ExceptionHandler(UsageHistoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response UsageHistoryNotFoundExceptionAdvice(UsageHistoryNotFoundException e) {
        return Response.failure(404, "회원님의 물품 이용내역을 찾을 수 없습니다.");
    }
}
