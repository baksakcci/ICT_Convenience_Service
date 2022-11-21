package com.example.sinabro.dto.item;

import com.example.sinabro.entity.item.Item;
import com.example.sinabro.entity.item.IsRental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemResponseDto {
    private String itemName;
    private String itemDetailName;
    private IsRental isRental;

    public ItemResponseDto(Item item) {
        this.itemName = item.getItemName();
        this.itemDetailName = item.getItemDetailName();
        this.isRental = item.getIsRental();
    }

    public static ItemResponseDto toDto(Item item) {
        return new ItemResponseDto(item.getItemName(), item.getItemDetailName()
                , item.getIsRental());
    }
}
