package com.example.sinabro.dto.item;

import com.example.sinabro.entity.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class ItemResponseListDto {
    private Integer totalPages;
    private Long totalCount;
    private Integer pageNumber;
    private List<ItemResponseDto> itemDtoList;

    public static ItemResponseListDto toDto(Integer totalPages, Long totalCount, Integer pageNumber, List<Item> itemList) {
        List<ItemResponseDto> itemResponseList = itemList.stream().map(ItemResponseDto::new).collect(Collectors.toList());
        return new ItemResponseListDto(totalPages, totalCount, pageNumber, itemResponseList);
    }
}
