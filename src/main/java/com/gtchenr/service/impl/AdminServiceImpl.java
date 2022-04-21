package com.gtchenr.service.impl;

import com.gtchenr.mapper.UserMapper;
import com.gtchenr.pojo.User;
import com.gtchenr.service.AdminService;
import com.gtchenr.utils.MybatisUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private UserMapper userMapper = MybatisUtil.getSqlSession().getMapper(UserMapper.class);

    @Override
    public List<User> Users() {
        return userMapper.queryAll();
    }
}
