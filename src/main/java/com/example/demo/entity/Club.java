package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Club {

    @Id
    @GeneratedValue
    private Long club_id;

    private String clubName;

    @OneToMany(mappedBy = "club")
    private List<Member> memberList = new ArrayList<Member>();

    public Club(){}

    public Club(String clubName){
        this.clubName = clubName;
    }

}
