package com.example.sinabro.entity.user;

import lombok.Getter;

@Getter
public enum UsersRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UsersRole(String value) {
        this.value = value;
    }

    private String value;
}
