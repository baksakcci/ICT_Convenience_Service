package com.example.sinabro.dto.usageHistory;

import com.example.sinabro.entity.usagehistory.UsageHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsageHistoryResponseDto {
    private String itemName;

    public static UsageHistoryResponseDto toDto(UsageHistory usageHistory) {
        return new UsageHistoryResponseDto(usageHistory.getItemName());
    }
}
