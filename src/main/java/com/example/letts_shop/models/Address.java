package com.example.letts_shop.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Address extends AbstractEntity{

    private String streetAddress;

    private String state;

    private String zipCode;

    private String aptNumber;

    private String country;

//    @OneToMany(mappedBy = "address")
//    private Member member;



}
