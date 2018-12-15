package com.decouple.service;/*
 * @Author Administrator
 * @Description //TODO
 * @Date 2018/12/11/011
 **/

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Override
    public void login() {
        System.out.println("login");
    }

    @Override
    public void getInfo() {
        System.out.println("getInfo");
    }
}
