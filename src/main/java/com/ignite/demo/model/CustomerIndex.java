package com.ignite.demo.model;

import java.util.UUID;

public class CustomerIndex {

    private String lastName;
    private UUID id;

    public CustomerIndex(String lastName, UUID id) {
        this.lastName = lastName;
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
