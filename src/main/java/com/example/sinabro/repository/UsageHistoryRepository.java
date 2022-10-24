package com.example.sinabro.repository;

import com.example.sinabro.entity.usagehistory.UsageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageHistoryRepository extends JpaRepository<UsageHistory, Long> {
}
