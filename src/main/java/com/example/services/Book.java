package com.example.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
public class Book {

    public JsonNode getBookDetail() {
        try {
            String output;
            URL url = new URL("https://scb-test-book-publisher.herokuapp.com/books/recommendation");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            log.info("Call Services url : "+url.toString());
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            StringBuffer stringBuffer = new StringBuffer();
            while ((output = br.readLine()) != null) {
                stringBuffer.append(output);
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(stringBuffer.toString());
            log.info("Data Respronse : "+rootNode);
            conn.disconnect();
            return rootNode;

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
            return null;
        }
    }
}
