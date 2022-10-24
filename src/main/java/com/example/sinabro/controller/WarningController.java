package com.example.sinabro.controller;

import com.example.sinabro.dto.warning.WarningRequestDto;
import com.example.sinabro.response.Response;
import com.example.sinabro.service.WarningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WarningController {

    private final WarningService warningService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{id}/warnings")
    public Response createWarning(@Valid @RequestBody WarningRequestDto wrt) {
        warningService.createWarning(wrt);
        return Response.success("경고 생성 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{id}/warnings/{warnId}")
    public Response updateWarning(@PathVariable("warnId") Long id, @Valid @RequestBody WarningRequestDto wrt) {
        warningService.updateWarning(wrt, id);
        return Response.success("경고 수정 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{id}/warnings/{warnId}")
    public Response deleteWarning(@PathVariable("warnId") Long id) {
        warningService.deleteWarning(id);
        return Response.success("경고 삭제 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}/warnings/{warnId}")
    public Response getWarning(@PathVariable("warnId") Long id) {
        return Response.success(warningService.findWarning(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}/warnings/")
    public Response getWarningAll() {
        return Response.success(warningService.findWarningAll());
    }
}
