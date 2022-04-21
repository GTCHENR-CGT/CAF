package com.gtchenr.utils;

import com.gtchenr.pojo.Role;
import com.gtchenr.pojo.User;
import org.junit.Test;


public class TokenUtilTest {

    @Test
    public void getTokenTest() {

        System.out.println(TokenUtil.getAccessToken(getUser()));
    }

    @Test
    public void parseTokenTest() {
//        String jws = TokenUtils.getAccessToken(getUser());
//        User user = TokenUtils.parseAccessToken(jws);
//        System.out.println(user);
    }

    public User getUser() {
        User user = new User();
        user.setUserId(1);
        user.setLoginName("gtchenr");
        Role role = new Role(Role.SUPER_ADMINISTRATION, Role.HAVE_PERMISSION);
        role.setRoleId(1);
        user.setRole(role);
        return user;
    }
}
