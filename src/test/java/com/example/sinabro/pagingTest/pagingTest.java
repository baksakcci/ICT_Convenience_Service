package com.example.sinabro.pagingTest;

import com.example.sinabro.dto.item.ItemListDto;
import com.example.sinabro.dto.item.ItemResponseDto;
import com.example.sinabro.entity.item.Item;
import com.example.sinabro.repository.ItemRepository;
import com.example.sinabro.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class pagingTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    @Test
    public void createTest() throws Exception {

        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
        Page<Item> result = itemRepository.findAll(pageable);


        Assertions.assertThat(result).isEqualTo(null);
    }
}
