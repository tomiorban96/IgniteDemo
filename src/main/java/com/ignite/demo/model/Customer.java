package com.ignite.demo.model;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Customer {

    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private IdTypeEnum idType;
    private String idNum;

    public Customer(){
        this.id = UUID.randomUUID();
    }

    public Customer(String firstName, String lastName, Date birthDate, String idNum) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.idNum = idNum;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}