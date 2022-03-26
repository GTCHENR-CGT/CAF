package com.gtchenr.utils;

import com.gtchenr.pojo.Role;
import com.gtchenr.pojo.User;
import org.junit.Test;

import java.util.UUID;


public class JwtUtilTest {

    @Test
    public void getHeaderTest() {
        System.out.println(JwtUtil.getHeader());
    }

    @Test
    public void getPayLoadTest(){
        User user = new User();
        user.setUserId(1);
        user.setLoginName("gtchenr");
        Role role = new Role(Role.SUPER_ADMINISTRATION,Role.HAVE_PERMISSION);
        role.setRoleId(1);
        user.setRole(role);
        String payLoad = JwtUtil.getPayLoad(user);
        System.out.println(payLoad);
    }

    @Test
    public void generateSignatureTest(){
        User user = new User();
        user.setUserId(1);
        user.setLoginName("gtchenr");
        Role role = new Role(Role.SUPER_ADMINISTRATION,Role.HAVE_PERMISSION);
        role.setRoleId(1);
        user.setRole(role);
        String s = JwtUtil.generateSignature(user);
        System.out.println(s);
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
