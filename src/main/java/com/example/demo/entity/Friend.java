package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Friend {

    private String friendName;
    private String friendPhone;

    public Friend(){}

    public Friend(String friendName, String friendPhone){
        this.friendName = friendName;
        this.friendPhone = friendPhone;
    }

}
