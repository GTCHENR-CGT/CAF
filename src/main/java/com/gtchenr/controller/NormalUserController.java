package com.gtchenr.controller;

import com.gtchenr.pojo.User;
import com.gtchenr.service.impl.NormalUserServiceImpl;
import com.gtchenr.utils.TokenUtils;
import com.gtchenr.vo.AjaxResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("normalUser")
public class NormalUserController {

    @Autowired
    private NormalUserServiceImpl normalUserService;

    /**
     * 用户发起登录请求
     * @param loginName
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public AjaxResultVO login(@RequestParam("loginName") String loginName, @RequestParam("password") String password, HttpServletRequest request) {
        System.out.println("用户发起登录请求！");
        boolean flag = normalUserService.login(loginName, password);
        //生成一个token
        if(flag){
            String accessToken  = request.getHeader("accessToken");
            String refreshToken = request.getHeader("refreshToken");
            if(TokenUtils.parseRefreshToken(refreshToken)){
                User user = TokenUtils.parseAccessToken(accessToken,refreshToken);
                if(loginName.equals(user.getLoginName())){
                    return new AjaxResultVO<String>(200,"success",null);
                }else {
                    System.out.println("token和登录名不一致！需要重新获取token");
                }
            }
            User user = normalUserService.user(loginName);
            accessToken = TokenUtils.getAccessToken(user);
            refreshToken = TokenUtils.getRefreshToken(user);
            List<String> datalist = new ArrayList<>();
            datalist.add(accessToken);
            datalist.add(refreshToken);
            return new AjaxResultVO<List<String>>(200, "success", datalist);
        }
        return new AjaxResultVO(201,"fail",null);
    }

    @RequestMapping("/hello.do")
    public ModelAndView add() {
        System.out.println("NormalUserController------------add()");
        ModelAndView mv = new ModelAndView();
        mv.addObject("teamName", "湖人队");
        mv.setViewName("index");//经过视图解析器，转化成物理资源路径，相当于request.getRequestDispatcher(index.jsp).forward();
        return mv;
    }

    @ResponseBody
    @RequestMapping("test01")
    public String test01() {
        return "cgt";//这样效果和返回 mv.setViewName("index")是一样的
    }

    @ResponseBody
    @RequestMapping(value = "test2", method = RequestMethod.GET)
    public AjaxResultVO test2() {
        String name = "gcthenr";
        return new AjaxResultVO<String>(200, "success", name);
    }

    @RequestMapping("hello")
    public String hello(){

        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "authorization",method = RequestMethod.POST)
    public AjaxResultVO authorization(HttpServletRequest request){
        String token = request.getHeader("refreshToken");
        if(token == null || !TokenUtils.parseRefreshToken(token))
            return new AjaxResultVO<Boolean>(200,"success",false);
        return new AjaxResultVO<Boolean>(200,"success",true);
    }
}

