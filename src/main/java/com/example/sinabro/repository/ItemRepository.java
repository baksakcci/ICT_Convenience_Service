package com.example.sinabro.repository;

import com.example.sinabro.dto.item.ItemNameResponseDto;
import com.example.sinabro.dto.item.ItemNameResponseInterface;
import com.example.sinabro.entity.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    /* paging  */
    Page<Item> findAll(Pageable pageable);
    /* paging + searching */
    Page<Item> findByItemNameContaining(String keyword, Pageable pageable);

    @Query(value = "SELECT i.itemName AS itemName FROM Item i GROUP BY i.itemName", nativeQuery = true)
    List<ItemNameResponseInterface> findGroupByItemNameWithJPQL();

    Optional<Item> findByItemDetailName(String itemDetailName);

    List<Item> findByItemNameContaining(String itemName);
}
