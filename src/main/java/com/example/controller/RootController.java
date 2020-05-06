package com.example.controller;

import com.example.model.*;
import com.example.services.Book;
import com.example.services.UserAuten;
import com.example.services.UserManage;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;



@RestController
public
class RootController {

    @Autowired
    private UserManage userManage;

    @Autowired
    private UserAuten userAuten;

    @Autowired
    private Book book;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResData getUser() {
        return new ResData(HttpStatus.OK.value(), new User(1, "john" ,"doe" ,"14/04/2000" , "[1,4]"));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public int loginAuthen(@RequestBody Login login) {
        try {
            return userAuten.getAuten(login);
        } catch (final Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public int creatUser(@RequestBody Login login) {
        try {
            return userManage.createUser(login);
        } catch (final Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }


    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public int deleteUser(@RequestBody Login login) {
        try {
            int dataLogin = userAuten.getAuten(login);
            if(200==dataLogin){
                return userManage.deleteUser(login);
            }else
                return HttpStatus.NOT_FOUND.value();
        } catch (final Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }

    @RequestMapping(value = "/users/orders", method = RequestMethod.POST)
    public ResDataPrice ordersUser(@RequestBody Login loginDto) {
        try {
//            Login login = new Login();
//            login.setUsername(loginDto.getUsername());
//            login.setPassword(loginDto.getPassword());
//            login.setDateOfBirth(loginDto.getDateOfBirth());
            int dataLogin = userAuten.getAuten(loginDto);
            if(200==dataLogin){
                String price = userManage.getOrder(loginDto);
              if(!price.equalsIgnoreCase("0.00"))
                return new ResDataPrice(HttpStatus.OK.value() , new Double(price+".00"));
               else return new ResDataPrice(HttpStatus.NOT_FOUND.value(),  new Double(price+".00"));
            }
            else return new ResDataPrice(HttpStatus.NOT_FOUND.value(), 0.00);
        } catch (final Exception e) {
            return new ResDataPrice(HttpStatus.INTERNAL_SERVER_ERROR.value(), 0.00);
        }
    }


    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResDataBook getbook() {
        JsonNode res = book.getBookDetail();
        if(null!=res){
            return new ResDataBook(HttpStatus.OK.value(),book.getBookDetail());
        }else
            return new ResDataBook(HttpStatus.INTERNAL_SERVER_ERROR.value(),book.getBookDetail());
    }


}
