package com.example.sinabro.controller;

import com.example.sinabro.response.Response;
import com.example.sinabro.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{studentId}")
public class RentalController {

    private final RentalService rentalService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rentals")
    public Response getRentals() {
        return Response.success(rentalService.getRentalAll());
    }
}
