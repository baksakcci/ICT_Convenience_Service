package com.example.sinabro.service;

import com.example.sinabro.dto.item.ItemListDto;
import com.example.sinabro.dto.item.ItemRequestDto;
import com.example.sinabro.dto.item.ItemResponseDto;
import com.example.sinabro.entity.item.Item;
import com.example.sinabro.exception.ItemNotFoundException;
import com.example.sinabro.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        itemRepository.deleteById(id);
    }

    /* paging */
    @Transactional(readOnly = true)
    public ItemListDto findItemAll(Pageable pageable) {
        Page<Item> itemPage = itemRepository.findAll(pageable);
        ItemListDto itemListDto = ItemListDto.toDto(itemPage.getTotalPages(), itemPage.getTotalElements(), itemPage.getNumber(), itemPage.getContent());
        return itemListDto;
    }

    @Transactional(readOnly = true)
    public ItemResponseDto findItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        return ItemResponseDto.toDto(item);
    }
}
