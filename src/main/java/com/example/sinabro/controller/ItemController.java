package com.example.sinabro.controller;

import com.example.sinabro.dto.item.ItemRequestDto;
import com.example.sinabro.response.Response;
import com.example.sinabro.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    /* paging */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getItems(@PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // http://localhost:8080/api/boards/?page=0
        return Response.success(itemService.findItemAll(pageable));
    }
    /* paging + searching */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public Response SearchItems(@RequestParam(value = "keyword") String keyword, @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.success(itemService.searchItem(pageable, keyword));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response getItem(@PathVariable("id") Long id) {
        return Response.success(itemService.findItem(id));
    }


}
