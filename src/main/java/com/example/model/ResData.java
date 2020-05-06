package com.example.model;

import lombok.Data;

@Data
public class ResData {
    private int statusCode;
    private User userData;
    //private Double price ;

    public ResData(int statusCode , User userData) {
        this.statusCode = statusCode;
        this.userData = userData;
    }

//    public ResData(int statusCode , Double price) {
//        this.statusCode = statusCode;
//        this.price = price;
//    }
}
