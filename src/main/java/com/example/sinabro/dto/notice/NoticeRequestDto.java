package com.example.sinabro.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoticeRequestDto {
    @NotNull(message = "타이틀을 입력하세요")
    private String title;
    @NotNull(message = "타이틀에 대한 자세한 설명을 입력하세요")
    private String content;
}
