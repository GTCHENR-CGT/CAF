package com.gtchenr.controller;


import com.gtchenr.mapper.UserMapper;
import com.gtchenr.pojo.Report;
import com.gtchenr.pojo.User;
import com.gtchenr.service.AdminService;
import com.gtchenr.service.NormalUserService;
import com.gtchenr.service.ReportService;
import com.gtchenr.utils.ELKUtil;
import com.gtchenr.utils.MybatisUtil;
import com.gtchenr.utils.TokenUtil;
import com.gtchenr.vo.ResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private NormalUserService normalUserService;
    @Autowired
    private ReportService reportService;

    /**
     * 管理员登录API
     * @param loginName
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO adminLogin(@RequestParam("loginName")String loginName, @RequestParam("password")String password, HttpServletRequest request){
        System.out.println("管理员发起登录请求！");
        boolean flag = normalUserService.login(loginName, password);
        //生成一个token
        if (flag) {
            String accessToken = request.getHeader("accessToken");
            String refreshToken = request.getHeader("refreshToken");
            if (TokenUtil.parseRefreshToken(refreshToken)) {
                User user = TokenUtil.parseAccessToken(accessToken, refreshToken);
                if (loginName.equals(user.getLoginName())) {
                    return new ResultVO<String>(200, "success", null);
                } else {
                    System.out.println("token和登录名不一致！需要重新获取token");
                }
            }
            User user = normalUserService.user(loginName);
            System.out.println(user);
            accessToken = TokenUtil.getAccessToken(user);
            refreshToken = TokenUtil.getRefreshToken(user);
            List<String> datalist = new ArrayList<>();
            datalist.add(accessToken);
            datalist.add(refreshToken);
            return new ResultVO<List<String>>(201, "success", datalist);
        }
        return new ResultVO(202, "fail", null);
    }

    /**
     * 查看所有用户信息
     * @return
     */
    @RequestMapping(value = "users",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO users(){

        return new ResultVO(201, "success", reportService.reports());
    }

    /**
     * 通过用户id删除用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "user/delete/{userId}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultVO deleteUser(@PathVariable("userId") Integer id){
        Integer delete = reportService.delete(id);
        if(delete == 1){
            return new ResultVO(201, "success", null);
        }
        return new ResultVO(202, "fail", null);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "user/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO updateUser(@Param("user") User user){


        return new ResultVO(201, "success", reportService.reports());
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping(value = "user/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO addUser(@Param("user") User user){

        return new ResultVO(201, "success", reportService.reports());
    }

    /**
     * 查看所有报告信息
     * @return
     */
    @RequestMapping(value = "reports",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO reports(){

        return new ResultVO(201, "success", reportService.reports());
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "report/delete/{reportId}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultVO deleteReport(@PathVariable("reportId") Integer id){
        System.out.println("report/delete/"+id);
        Integer delete = reportService.delete(id);
//        ELKUtil.delete("report",)
        if(delete == 1){
            return new ResultVO(201, "success", null);
        }
        return new ResultVO(202, "fail", null);
    }

    @RequestMapping(value = "report/update",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO updateReport(@Param("report")Report report){


        return new ResultVO(201, "success", reportService.reports());
    }

    @RequestMapping(value = "report/add",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO addReport(@Param("report")Report report){

        return new ResultVO(201, "success", reportService.reports());
    }
}
