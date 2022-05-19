package com.gtchenr.mapper;

import com.gtchenr.pojo.User;

import java.util.List;

public interface UserMapper {

    /**
     * 通过账号和密码验证登录
     *
     * @param loginName
     * @param password
     * @return
     */
    Integer login(String loginName, String password);

    Integer exist(String loginName);

    User queryUserByUserId(Integer userId);

    List<User> queryAll();

    Integer add(User user);

    User queryUserByLoginName(String loginName);

    Integer update(User user);

    Integer delete(Integer userId);
}
