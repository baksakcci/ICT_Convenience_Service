package com.example.sinabro.entity.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * 아이템 Entity
 *
 * id (Primary Key)
 * itemname : 아이템 이름
 * itemimage : 아이템 이미지
 */
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

    @Column(nullable = false, unique = true)
    private String itemDetailName;
    @Column(nullable = false)
    private Long EA;

    @Column(nullable = false)
    private String use;

    public Item(String itemName, String itemDetailName, Long EA, String use) {
        this.itemName = itemName;
        this.itemDetailName = itemDetailName;
        this.EA = EA;
        this.use = use;
    }

    public Item editItem(String itemName, String itemDetailName, Long EA, String use) {
        this.itemName = itemName;
        this.itemDetailName = itemDetailName;
        this.EA = EA;
        this.use = use;
        return this;
    }
}
