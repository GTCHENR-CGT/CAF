package com.gtchenr.service.impl;


import com.gtchenr.mapper.RoleMapper;
import com.gtchenr.mapper.UserMapper;
import com.gtchenr.pojo.Role;
import com.gtchenr.pojo.User;
import com.gtchenr.service.NormalUserService;
import com.gtchenr.utils.MybatisUtil;
import org.springframework.stereotype.Service;


@Service
public class NormalUserServiceImpl implements NormalUserService {

    private UserMapper userMapper = MybatisUtil.getSqlSession().getMapper(UserMapper.class);
    private RoleMapper roleMapper = MybatisUtil.getSqlSession().getMapper(RoleMapper.class);

    @Override
    public boolean login(String loginName, String password) {
        if (userMapper.login(loginName, password) == 1)
            return true;
        return false;
    }

    @Override
    public User user(String loginName) {
        User user = userMapper.queryUserByLoginName(loginName);
        user.setRole(role(user));
        return user;
    }

    @Override
    public User user(Integer userId) {
        User user = userMapper.queryUserByUserId(userId);
        user.setRole(role(user));
        return userMapper.queryUserByUserId(userId);
    }

    @Override
    public Role role(User user) {
        return roleMapper.queryByRoleId(user.getRoleId());
    }


}
