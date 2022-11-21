package com.example.sinabro.entity.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private String itemDetailName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IsRental isRental;

    public Item(String itemName, String itemDetailName, IsRental isRental) {
        this.itemName = itemName;
        this.itemDetailName = itemDetailName;
        this.isRental = isRental;
    }

    public Item editItem(String itemName, String itemDetailName, IsRental isRental) {
        this.itemName = itemName;
        this.itemDetailName = itemDetailName;
        this.isRental = isRental;
        return this;
    }
}