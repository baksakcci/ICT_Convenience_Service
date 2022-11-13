package com.example.sinabro.controller;

import com.example.sinabro.dto.notice.NoticeRequestDto;
import com.example.sinabro.exception.UserNotAdminException;
import com.example.sinabro.response.Response;
import com.example.sinabro.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{id}")
public class NoticeController {

    private final NoticeService noticeService;

    /*
    [[ User ]]
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/notices")
    public Response getNoticeAll() {
        return Response.success(noticeService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/notices/search")
    public Response searchNotices(@RequestParam(value = "keyword") String keyword) {
        return Response.success(noticeService.searchNotice(keyword));
    }

    /*
    [[ Admin ]]
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/notices")
    public Response createNotice(@PathVariable("id") Long id
            , @RequestBody NoticeRequestDto noticeRequestDto) {
        noticeService.createNotice(id, noticeRequestDto);
        return Response.success("사용 내역 생성 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/notices/{noticeId}")
    public Response updateNotice(@PathVariable("id") Long id
            , @PathVariable("noticeId") Long noticeId, @RequestBody NoticeRequestDto noticeRequestDto) {
        noticeService.updateNotice(id, noticeRequestDto, noticeId);
        return Response.success("사용 내역 수정 완료");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/notices/{noticeId}")
    public Response deleteNotice(@PathVariable("id") Long id
            , @PathVariable("noticeId") Long noticeId) {
        noticeService.deleteNotice(id, noticeId);
        return Response.success("사용 내역 삭제 완료");
    }
}
