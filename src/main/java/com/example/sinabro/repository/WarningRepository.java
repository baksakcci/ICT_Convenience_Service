package com.example.sinabro.repository;

import com.example.sinabro.entity.warnning.Warning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarningRepository extends JpaRepository<Warning, Long> {
}
