package com.example.sinabro.dto.usageHistory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsageHistoryRequestDto {

    @NotBlank(message = "어떤 아이템을 사용했는지 입력해주세요")
    private String itemName;
}
