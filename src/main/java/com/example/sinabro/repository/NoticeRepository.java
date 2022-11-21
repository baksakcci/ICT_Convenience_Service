package com.example.sinabro.repository;

import com.example.sinabro.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
