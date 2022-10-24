package com.example.sinabro.dto.warning;

import com.example.sinabro.entity.warnning.Warning;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarningResponseDto {
    private String warning;
    private String message;

    public static WarningResponseDto toDto(Warning warning) {
        return new WarningResponseDto(warning.getWarning(), warning.getMessage());
    }
}
