package com.example.sinabro.service;

import com.example.sinabro.dto.item.ItemRequestDto;
import com.example.sinabro.dto.item.ItemResponseDto;
import com.example.sinabro.entity.item.Item;
import com.example.sinabro.exception.ItemNotFoundException;
import com.example.sinabro.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    @Transactional
    public void createItem(ItemRequestDto itemRequestDto) {
        Item item = new Item(itemRequestDto.getItemName(), itemRequestDto.getItemDetailName(), itemRequestDto.getEA(), itemRequestDto.getUse());
        itemRepository.save(item);
    }
    @Transactional
    public void updateItem(ItemRequestDto ite, Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        item.editItem(ite.getItemName(), ite.getItemDetailName(), ite.getEA(), ite.getUse());
    }

    @Transactional
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ItemResponseDto> findItemAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponseDto> result = new ArrayList<>();
        for (Item item : items) {
            result.add(ItemResponseDto.toDto(item));
        }
        return result;
    }
}
