package com.example.sinabro.repository;

import com.example.sinabro.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByTitleContaining(String keyword);

    Optional<Notice> findByUser_Id(Long id);

    Optional<Notice> findByUser_StudentId(String StudentId);
}
