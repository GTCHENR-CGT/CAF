package com.gtchenr.mapper;

import com.gtchenr.pojo.User;
import com.gtchenr.utils.MybatisUtil;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {

    UserMapper userMapper = MybatisUtil.getSqlSession().getMapper(UserMapper.class);

    @Test
    public void loginTest() {
        System.out.println(userMapper.login("gtchenr", "123456"));
    }

    @Test
    public void existTest() {
        System.out.println(userMapper.exist("guan"));
    }

    @Test
    public void queryUserByUserIdTest() {
        User user = userMapper.queryUserByUserId(2);
        System.out.println(user);
    }

    @Test
    public void queryAllTest() {
        List<User> users = userMapper.queryAll();
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void addTest() {
        User user = new User();
        user.setRoleId(6);
        user.setLoginName("chen1");
        user.setPassword("123");
        userMapper.add(user);
        MybatisUtil.getSqlSession().commit();
    }

    @Test
    public void userTest() {

        User user = userMapper.queryUserByLoginName("gtchenr");
        System.out.println(user);
    }

    @Test
    public void updateTest(){
        User user = userMapper.queryUserByUserId(1);
        user.setEmail("gtchenr@gmail.com");
        Integer update = userMapper.update(user);
        System.out.println(update);
    }
}
