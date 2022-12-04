package com.example.sinabro.dto.union;

import com.example.sinabro.entity.union.Union;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnionResponseDto {
    private boolean isOpen;

    public UnionResponseDto(Union union) {
        this.isOpen = union.isOpen();
    }

    public static UnionResponseDto toDto(Union union) {
        return new UnionResponseDto(union.isOpen());
    }
}
