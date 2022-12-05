package com.example.sinabro.service;

import com.example.sinabro.dto.item.ItemResponseListDto;
import com.example.sinabro.dto.item.ItemResponseDto;
import com.example.sinabro.entity.item.Item;
import com.example.sinabro.exception.ItemNotFoundException;
import com.example.sinabro.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    /*
    [[ User ]]
     */
    // paging
    @Transactional(readOnly = true)
    public ItemResponseListDto findItemAll(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAll(pageable);
        ItemResponseListDto itemResponseListDto = ItemResponseListDto.toDto(itemPage.getTotalPages(), itemPage.getTotalElements(), itemPage.getNumber(), itemPage.getContent());
        return itemResponseListDto;
    }
    // paging + search
    @Transactional(readOnly = true)
    public ItemResponseListDto searchItem(Pageable pageable, String keyword) {
        Page<Item> itemPage = itemRepository.findByItemNameContaining(keyword, pageable);
        ItemResponseListDto itemResponseListDto = ItemResponseListDto.toDto(itemPage.getTotalPages(), itemPage.getTotalElements(), itemPage.getNumber(), itemPage.getContent());
        return itemResponseListDto;
    }
    @Transactional(readOnly = true)
    public ItemResponseDto findItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        return ItemResponseDto.toDto(item);
    }

    @Transactional(readOnly = true)
    public List<ItemResponseDto> findByItemName(String itemName) {
        List<Item> items = itemRepository.findByItemName(itemName);
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item i : items) {
            ItemResponseDto itemResponseDto = ItemResponseDto.toDto(i);
            itemResponseDtos.add(itemResponseDto);
        }
        return itemResponseDtos;
    }
}
