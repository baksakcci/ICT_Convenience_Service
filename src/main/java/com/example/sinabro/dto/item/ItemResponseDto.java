package com.example.sinabro.dto.item;

import com.example.sinabro.entity.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemResponseDto {
    private String itemName;
    private String itemDetailName;
    private Long EA;
    private String use;
    public static ItemResponseDto toDto(Item item) {
        return new ItemResponseDto(item.getItemName(), item.getItemDetailName()
                , item.getEA(), item.getUse());
    }
}
