package com.example.sinabro.controller;

import com.example.sinabro.dto.item.ItemRequestDto;
import com.example.sinabro.response.Response;
import com.example.sinabro.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/item")
    public Response createItem(@Valid @RequestBody ItemRequestDto itemRequestDto) {
        itemService.createItem(itemRequestDto);
        return Response.success("물품 생성 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/item/{id}")
    public Response updateItem(@PathVariable("id") Long id, @Valid @RequestBody ItemRequestDto itemRequestDto) {
        itemService.updateItem(itemRequestDto, id);
        return Response.success("물품 수정 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/item/{id}")
    public Response deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return Response.success("물품 삭제 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/item")
    public Response getItems() {
        return Response.success(itemService.findItemAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/item/{id}")
    public Response getItem(@PathVariable("id") Long id) {
        return Response.success(itemService.findItem(id));
    }


}
