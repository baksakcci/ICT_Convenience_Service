package com.example.sinabro.advice;

import com.example.sinabro.entity.item.Item;
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

    /*
    User
     */
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
        return Response.failure(404, "DB에 저장된 회원정보를 찾을 수 없습니다.");
    }

    /*
    admin
     */
    @ExceptionHandler(UserNotAdminException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response UserNotAdminExceptionAdvice(UserNotAdminException e) {
        return Response.failure(401, "관리자 권한이 아닙니다.");
    }

    /*
    Item
     */
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response ItemNotFoundExceptionAdvice(ItemNotFoundException e) {
        return Response.failure(404, "물품을 찾을 수 없습니다.");
    }
    @ExceptionHandler(ItemDontCreateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response ItemDontCreateExceptionAdvice(ItemDontCreateException e) {
        return Response.failure(400, "같은 이름을 가진 물품이 있습니다.");
    }

    @ExceptionHandler(ItemCanNotUseException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response ItemCanNotUseExceptionAdvice(ItemCanNotUseException e) {
        return Response.failure(405, "물품이 사용중입니다. 물품이 대여 가능상태가 아닙니다.");
    }
    /*
    Notice
     */
    @ExceptionHandler(NotionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response NoticeNotFoundExceptionAdvice(NotionNotFoundException e) {
        return Response.failure(404, "DB에 저장된 알림이 없습니다.");
    }


}
