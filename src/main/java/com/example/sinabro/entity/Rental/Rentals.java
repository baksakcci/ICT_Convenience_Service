package com.example.sinabro.entity.Rental;

import com.example.sinabro.entity.item.Item;
import com.example.sinabro.entity.user.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rentals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column()
    @ColumnDefault("0")
    private LocalDate borrowDate; // 날짜

    // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    @PrePersist
    public void createDate() {
        this.borrowDate = LocalDate.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersId", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Item item;

    public Rentals(String contents, Users users, Item item) {
        this.contents = contents;
        this.users = users;
        this.item = item;
    }



}
