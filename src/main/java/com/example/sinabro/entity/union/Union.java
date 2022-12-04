package com.example.sinabro.entity.union;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Union {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private boolean isOpen;

    public Union(boolean isOpen) {
        this.isOpen = isOpen;
    }
    public void setIsOpen(boolean b) {
        this.isOpen = b;
    }

}
