package com.example.model;

import lombok.Data;

@Data
public class ResDataPrice {
    private int statusCode;
    //private User userData;
    private Double price ;

//    public ResDataPrice(int statusCode , User userData) {
//        this.statusCode = statusCode;
//        this.userData = userData;
//    }

    public ResDataPrice(int statusCode , Double price) {
        this.statusCode = statusCode;
        this.price = price;
    }
}
