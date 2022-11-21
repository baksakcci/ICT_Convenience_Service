package com.example.sinabro.service;

import com.example.sinabro.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private NoticeRepository noticeRepository;


}
