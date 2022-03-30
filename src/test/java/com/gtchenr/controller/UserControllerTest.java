package com.gtchenr.controller;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NormalUserControllerTest {

    @Test
    public void loginTest(){
        String springConfig = "springmvc.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
        NormalUserController normalUserController = (NormalUserController) context.getBean("normalUserController");
    }
}
