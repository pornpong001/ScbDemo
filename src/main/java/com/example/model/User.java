package com.example.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
//import org.springframework.data.cassandra.core.mapping.Table;

@Data
public class User {
    private int id;
    private String name;
    private String lastname ;
    private String dateOfBirth ;
    private String books;


    public User(int id, String name , String lastname , String dateOfBirth , String books) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth =dateOfBirth;
        this.books = books;
    }
}
