package com.gtchenr.controller;


import com.gtchenr.mapper.UserMapper;
import com.gtchenr.pojo.User;
import com.gtchenr.service.AdminService;
import com.gtchenr.utils.MybatisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("users")
    @ResponseBody
    public List<User> getAllUser() {
        return adminService.Users();
    }
}
