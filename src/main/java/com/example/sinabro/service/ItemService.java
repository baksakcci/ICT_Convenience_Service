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
    public void createItem(ItemRequestDto ite) {
        Item item = new Item(ite.getItemName(), ite.getItemDetailName(), ite.getUsed());
        itemRepository.save(item);
    }
    @Transactional
    public void updateItem(ItemRequestDto ite, Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        item.editItem(ite.getItemName(), ite.getItemDetailName(), ite.getUsed());
    }

    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
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

    @Transactional(readOnly = true)
    public ItemResponseDto findItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        return ItemResponseDto.toDto(item);
    }
}
