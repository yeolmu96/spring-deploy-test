package com.playdata.backend.monoproj.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class Account {

    @Id
    Long id;
    String email;
    String nickname;

    public Account(){

    }

    public Account(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}
