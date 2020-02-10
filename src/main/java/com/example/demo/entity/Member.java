package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int age;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    @Embedded
    private Friend friends;

}
