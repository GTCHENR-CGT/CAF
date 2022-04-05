package com.gtchenr.controller;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserControllerTest {

    @Test
    public void loginTest() {
        String springConfig = "springmvc.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
        UserController normalUserController = (UserController) context.getBean("userController");
    }
}
