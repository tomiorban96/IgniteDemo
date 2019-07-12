package com.ignite.demo.model;

import java.util.UUID;

public class Customer {

    private UUID id;
    private String firstName;
    private String lastName;
    private String fullName;

    public Customer(){
        this.id = UUID.randomUUID();
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.id = UUID.randomUUID();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.fullName = firstName + " " + lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
    }

    public String getFullName() {
        return fullName;
    }

}
