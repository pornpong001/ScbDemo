package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "user_login_detail")
public class Login {
/*    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;*/
    @Id
    String username;
    String password;

    @JsonProperty("date_of_birth")
    String dateOfBirth;
    String price;

    public Login() {
        super();
    }

    public Login(String username, String password){
        super();
        this.username = username;
        this.password = password;
    }

    public Login(String username, String password, String dateOfBirth) {
        super();
        this.username = username;
        this.password = password;
        this.dateOfBirth= dateOfBirth;
    }
}
