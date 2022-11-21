package com.example.sinabro.controller;

import com.example.sinabro.response.Response;
import com.example.sinabro.service.RentalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class RentalsController {

    private final RentalsService rentalsService;

    /*
    [[ Rental ]]
     */

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rentals")
    public Response getRental(@RequestParam(value = "studentId") String studentId) {
        return Response.success(rentalsService.findAll(studentId));
    }

}
