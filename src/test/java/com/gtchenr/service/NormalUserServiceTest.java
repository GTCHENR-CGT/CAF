package com.gtchenr.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class NormalUserServiceTest {

    @Test
    public void loginTest() {

        String springConfig = "application.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
        NormalUserService normalUserServiceImpl = (NormalUserService) context.getBean("normalUserServiceImpl");
    }


}
