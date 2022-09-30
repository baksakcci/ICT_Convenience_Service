package com.example.sinabro.advice;

import com.example.sinabro.exception.ItemNotFoundException;
import com.example.sinabro.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response ItemNotFoundExceptionAdvice(ItemNotFoundException e) {
        return Response.failure(404, "물품을 찾을 수 없습니다.");
    }
}
