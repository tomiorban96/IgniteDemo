package com.ignite.demo.model;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class CustomerIndex {

    private String lastName;
    private Set<UUID> ids;

}
