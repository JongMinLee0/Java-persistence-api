package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long member_id;

    private String name;
    private int age;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    @Embedded
    private Friend friends;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    public Member(){}

    public Member(String name, int age, Friend friend){
        this.name = name;
        this.age = age;
        this.friends = friend;
    }

}
