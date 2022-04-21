package com.gtchenr.service;

import com.gtchenr.pojo.Role;
import com.gtchenr.pojo.User;

public interface NormalUserService {

    boolean login(String loginName, String password);

    User user(String loginName);

    User user(Integer userId);

    Role role(User user);
}
