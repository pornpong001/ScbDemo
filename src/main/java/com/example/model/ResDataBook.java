package com.example.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class ResDataBook {
    private int statusCode;
    private JsonNode data;

    public ResDataBook(int statusCode , JsonNode data) {
        this.statusCode = statusCode;
        this.data = data;
    }

}
