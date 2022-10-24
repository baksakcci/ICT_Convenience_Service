package com.example.sinabro.controller;

import com.example.sinabro.dto.item.ItemRequestDto;
import com.example.sinabro.response.Response;
import com.example.sinabro.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    /* paging */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/items")
    public Response getItems(@PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // http://localhost:8080/api/boards/?page=0
        return Response.success(itemService.findItemAll(pageable));
    }

    /* paging + searching */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/items/search")
    public Response SearchItems(@RequestParam(value = "keyword") String keyword, @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.success(itemService.searchItem(pageable, keyword));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/items")
    public Response createItem(@Valid @RequestBody ItemRequestDto itemRequestDto) {
        itemService.createItem(itemRequestDto);
        return Response.success("물품 생성 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/items/{id}")
    public Response updateItem(@PathVariable("id") Long id, @Valid @RequestBody ItemRequestDto itemRequestDto) {
        itemService.updateItem(itemRequestDto, id);
        return Response.success("물품 수정 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/items/{id}")
    public Response deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return Response.success("물품 삭제 완료");
    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/items/{id}")
    public Response getItem(@PathVariable("id") Long id) {
        return Response.success(itemService.findItem(id));
    }


}
