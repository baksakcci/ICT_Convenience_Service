package com.example.sinabro.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsersRole usersRole;

    public Users(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
    }

    public Users loginUser(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
        return this;
    }
}
