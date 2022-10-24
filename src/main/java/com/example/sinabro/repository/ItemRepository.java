package com.example.sinabro.repository;

import com.example.sinabro.entity.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    /* paging  */
    Page<Item> findAll(Pageable pageable);
    /* paging + searching */
    Page<Item> findByItemNameContaining(String keyword, Pageable pageable);
}
