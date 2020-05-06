package com.example.services;

import com.example.controller.RootController;
import com.example.model.ResData;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {


	@Test
	void contextLoads() {
        Book book = new Book();

        JsonNode result = book.getBookDetail();

        assertEquals("[{\"book_name\":\"The Great Alone: A Novel Kristin Hannah\",\"price\":495,\"id\":4,\"author_name\":\"Kristin Hannah\"},{\"book_name\":\"An American Princess: The Many Lives of Allene Tew\",\"price\":149,\"id\":5,\"author_name\":\"Annejet van der Zijl, Michele Hutchison\"}]", result.toString());

    }

    @Test
    void contextLoads1() {
        RootController rootController = new RootController();
        //Book book = new Book();
        ResData result = rootController.getUser();


        assertEquals("[{\"book_name\":\"The Great Alone: A Novel Kristin Hannah\",\"price\":495,\"id\":4,\"author_name\":\"Kristin Hannah\"},{\"book_name\":\"An American Princess: The Many Lives of Allene Tew\",\"price\":149,\"id\":5,\"author_name\":\"Annejet van der Zijl, Michele Hutchison\"}]", result.toString());

    }
}
