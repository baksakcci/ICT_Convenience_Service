package com.example.sinabro.controller;

import com.example.sinabro.dto.item.ItemRequestDto;
import com.example.sinabro.dto.rental.RentalsRequestDto;
import com.example.sinabro.dto.user.UserAdminRequestDto;
import com.example.sinabro.dto.user.UsersLoginRequestDto;
import com.example.sinabro.response.Response;
import com.example.sinabro.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    /*
    [[ USER ADMIN ]]
    회원은 관리자만 추가할 수 있게 만듬(회원가입)
    학번이나 비밀번호 수정 기능 추가
    학번을 통한 검색 추가
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/signup")
    public Response signup(@Valid @RequestBody UserAdminRequestDto userAdminRequestDto){
        return Response.success(adminService.createUser(userAdminRequestDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{studentId}")
    public Response getUser(@PathVariable("studentId") String studentId) {
        return Response.success(adminService.findUser(studentId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public Response getUserAll() {
        return Response.success(adminService.findMemberAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/delete")
    public Response deleteUser(@RequestParam("name") String name) {
        adminService.deleteMember(name);
        return Response.success("회원 삭제 완료");
    }

    /*
    [[ ITEM RENTAL ADMIN ]]
    어떤 유저가 무슨 물품을 랜탈하고 있는지 확인
     */
    /* paging */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/items")
    public Response getItems() {
        // http://localhost:8080/api/boards/?page=0
        return Response.success(adminService.findItemAll());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/items")
    public Response createItem(@Valid @RequestBody ItemRequestDto itemRequestDto) {
        adminService.createItem(itemRequestDto);
        return Response.success("물품 생성 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/items/{id}")
    public Response updateItem(@PathVariable("id") Long id, @Valid @RequestBody ItemRequestDto itemRequestDto) {
        adminService.updateItem(itemRequestDto, id);
        return Response.success("물품 수정 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/items/{id}")
    public Response deleteItem(@PathVariable("id") Long id) {
        adminService.deleteItem(id);
        return Response.success("물품 삭제 완료");
    }

    /*
    [[ RENTAL ADMIN ]]
    대여 버튼을 클릭했을 때 대여했다고 추가
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/rentals")
    public Response clickRental(@Valid @RequestBody RentalsRequestDto rentalsRequestDto) {
        adminService.createRental(rentalsRequestDto);
        return Response.success("사용 내역 생성 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rentals")
    public Response getRentalAll() {
        return Response.success(adminService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/rentals/search")
    public Response searchRentals(@RequestParam(value = "keyword") String keyword) {
        return Response.success(adminService.searchRental(keyword));
    }

    /*
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/notices/{noticeId}")
    public Response updateNotice(@PathVariable("noticeId") Long noticeId, @Valid @RequestBody NoticeRequestDto noticeRequestDto) {
        adminService.updateNotice(noticeRequestDto, noticeId);
        return Response.success("사용 내역 수정 완료");
    }
     */

    /*
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/notices/{noticeId}")
    public Response deleteNotice(@PathVariable("noticeId") Long noticeId, @Valid @RequestBody RentalRequestDto rentalRequestDto) {
        adminService.deleteRental(rentalRequestDto, noticeId);
        return Response.success("사용 내역 삭제 완료");
    }

     */
}
