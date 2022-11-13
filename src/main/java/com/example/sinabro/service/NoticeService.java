package com.example.sinabro.service;

import com.example.sinabro.dto.notice.NoticeRequestDto;
import com.example.sinabro.dto.notice.NoticeResponseDto;
import com.example.sinabro.entity.member.User;
import com.example.sinabro.entity.notice.Notice;
import com.example.sinabro.exception.NotionNotFoundException;
import com.example.sinabro.exception.UserNotAdminException;
import com.example.sinabro.exception.UserNotFoundException;
import com.example.sinabro.repository.NoticeRepository;
import com.example.sinabro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<NoticeResponseDto> findAll() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeResponseDto> noticeResponseDtoList = new ArrayList<>();
        for(Notice n : notices) {
            NoticeResponseDto noticeResponseDto = NoticeResponseDto.toDto(n);
            noticeResponseDtoList.add(noticeResponseDto);
        }
        return noticeResponseDtoList;
    }

    @Transactional(readOnly = true)
    public NoticeResponseDto findNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow();
        NoticeResponseDto noticeResponseDto = NoticeResponseDto.toDto(notice);
        return noticeResponseDto;
    }

    @Transactional(readOnly = true)
    public List<NoticeResponseDto> searchNotice(String keyword) {
        List<Notice> notices = noticeRepository.findByTitleContaining(keyword);
        List<NoticeResponseDto> noticeResponseDtoList = new ArrayList<>();
        for(Notice n : notices) {
            NoticeResponseDto noticeResponseDto = NoticeResponseDto.toDto(n);
            noticeResponseDtoList.add(noticeResponseDto);
        }
        return noticeResponseDtoList;
    }

    @Transactional
    public void createNotice(Long id, NoticeRequestDto nrd) {
        // 회원 검증
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(!"ADMIN".equals(user.getUserRole().toString())) throw new UserNotAdminException();

        Notice notice = new Notice(nrd.getTitle(), nrd.getContent(), user);
        noticeRepository.save(notice);
    }

    @Transactional
    public void updateNotice(Long id, NoticeRequestDto nrd, Long noticeId) {
        // 회원 검증
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(!"ADMIN".equals(user.getUserRole().toString())) throw new UserNotAdminException();

        Notice notice = noticeRepository.findById(noticeId).orElseThrow(NotionNotFoundException::new); // DB 있는지 없는지 검사
        Notice editNotice = notice.editNotice(nrd.getTitle(), nrd.getContent());
        noticeRepository.save(editNotice);
    }

    @Transactional
    public void deleteNotice(Long id, Long noticeId) {
        // 회원 검증
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(!"ADMIN".equals(user.getUserRole().toString())) throw new UserNotAdminException();

        noticeRepository.findById(noticeId).orElseThrow(NotionNotFoundException::new);
        noticeRepository.deleteById(noticeId);
    }
}
