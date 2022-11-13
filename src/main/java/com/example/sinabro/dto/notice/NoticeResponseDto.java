package com.example.sinabro.dto.notice;

import com.example.sinabro.entity.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoticeResponseDto {

    private String studentId;

    private String title;

    private String content;

    public static NoticeResponseDto toDto(Notice notice) {
        return new NoticeResponseDto(notice.getUser().getStudentId(), notice.getTitle(), notice.getContent());
    }
}
