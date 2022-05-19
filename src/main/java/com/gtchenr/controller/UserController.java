package com.gtchenr.controller;

import com.gtchenr.mapper.CommentMapper;
import com.gtchenr.mapper.ReportMapper;
import com.gtchenr.mapper.UserMapper;
import com.gtchenr.pojo.Comment;
import com.gtchenr.pojo.Report;
import com.gtchenr.pojo.User;
import com.gtchenr.service.impl.NormalUserServiceImpl;
import com.gtchenr.utils.ELKUtil;
import com.gtchenr.utils.MybatisUtil;
import com.gtchenr.utils.TokenUtil;
import com.gtchenr.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("normalUser")
public class UserController {

    @Autowired
    private NormalUserServiceImpl normalUserService;
    private UserMapper userMapper = MybatisUtil.getSqlSession().getMapper(UserMapper.class);
    private ReportMapper reportMapper = MybatisUtil.getSqlSession().getMapper(ReportMapper.class);
    private CommentMapper commentMapper = MybatisUtil.getSqlSession().getMapper(CommentMapper.class);
    /**
     * 用户发起登录请求
     *
     * @param loginName
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultVO login(@RequestParam("loginName") String loginName, @RequestParam("password") String password, HttpServletRequest request) {
        System.out.println("用户发起登录请求！");
        boolean flag = normalUserService.login(loginName, password);
        System.out.println(flag);
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
            accessToken = TokenUtil.getAccessToken(user);
            refreshToken = TokenUtil.getRefreshToken(user);
            List<Object> datalist = new ArrayList<>();
            datalist.add(accessToken);
            datalist.add(refreshToken);
            datalist.add(user);
            return new ResultVO(200, "success", datalist);
        }
        return new ResultVO(201, "fail", null);
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
    public ResultVO test2() {
        String name = "gcthenr";
        return new ResultVO<String>(200, "success", name);
    }

    @RequestMapping("hello")
    public String hello() {

        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "authorization", method = RequestMethod.POST)
    public ResultVO authorization(HttpServletRequest request) {
        String token = request.getHeader("refreshToken");
        if (token == null || !TokenUtil.parseRefreshToken(token))
            return new ResultVO<Boolean>(200, "success", false);
        return new ResultVO<Boolean>(200, "success", true);
    }

    @ResponseBody
    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    public ResultVO getUserInfo(HttpServletRequest request) {
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        User user = TokenUtil.parseAccessToken(accessToken, refreshToken);
        if(user == null){
            return new ResultVO(201,"fail",null);
        }
        User user1 = userMapper.queryUserByUserId(user.getUserId());
        if(user1 == null)
            return new ResultVO(201,"fail",user1);
        return new ResultVO(201,"success",user1);
    }

    @ResponseBody
    @RequestMapping(value = "userInfo1", method = RequestMethod.GET)
    public ResultVO getUserInfo1(Integer userId) {
        User user = userMapper.queryUserByUserId(userId);
        if(user == null)
            return new ResultVO(201,"fail",user);
        return new ResultVO(201,"success",user);
    }

    @ResponseBody
    @RequestMapping(value = "myreports", method = RequestMethod.GET)
    public ResultVO getMyReports(Integer userId) {
        List<Report> reports = reportMapper.queryReportByUserId(userId);
        return new ResultVO(201,"success",reports);
    }

    @ResponseBody
    @RequestMapping(value = "publishComment", method = RequestMethod.POST)
    public ResultVO publishComment(Comment comment,HttpServletRequest request) {
        Integer flag = 0;
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        User user = TokenUtil.parseAccessToken(accessToken, refreshToken);
        if(comment != null & user != null){
            Date date = new Date();
            comment.setPublishTime(date);
            comment.setCommentCredit(0);
            comment.setUserId(user.getUserId());
            flag = commentMapper.add(comment);
        }
        if (flag == 1){
            return new ResultVO(201,"success",null);
        }
        return new ResultVO(201,"fail",null);
    }


    @ResponseBody
    @RequestMapping(value = "getReportByUserId", method = RequestMethod.GET)
    public ResultVO getReportByUserId(HttpServletRequest request) {
        Integer flag = 0;
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        User user = TokenUtil.parseAccessToken(accessToken, refreshToken);
        if(user != null){
            List<Report> reports = reportMapper.queryReportByUserId(user.getUserId());
            return new ResultVO(201,"success",reports);
        }
        return new ResultVO(201,"fail",null);
    }

    @RequestMapping(value = "getCommentsByUserId", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getCommentsByUserId(HttpServletRequest request) {
        Integer flag = 0;
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        User user = TokenUtil.parseAccessToken(accessToken, refreshToken);
        if(user != null){
            List<Comment> comments = commentMapper.queryCommentByUserId(user.getUserId());
            return new ResultVO(201,"success",comments);
        }
        return new ResultVO(201,"fail",null);
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO updateUser(User user,HttpServletRequest request) {
        Integer flag = 0;
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        User user1 = TokenUtil.parseAccessToken(accessToken, refreshToken);
        if(user1 != null && user1.getUserId()!=null){
            user.setUserId(user1.getUserId());
            flag= userMapper.update(user);
        }
        if (flag == 1)
            return new ResultVO(201,"success",null);
        return new ResultVO(201,"fail",null);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO updateUser(User user) {
        Integer flag = userMapper.add(user);
        if (flag == 1)
            return new ResultVO(201,"success",null);
        return new ResultVO(201,"fail",null);
    }

    @RequestMapping(value = "report/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO deleteReport(Integer reportId,HttpServletRequest request){

        System.out.println("============");
        System.out.println(reportId);
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        User user = TokenUtil.parseAccessToken(accessToken, refreshToken);
        if(user == null)
            return new ResultVO(202, "no login", null);
        System.out.println(user);
        List<Report> reports = reportMapper.queryReportByUserId(user.getUserId());
        Report report1 = new Report();
        report1.setReportId(reportId);
        boolean flag = false;
        for (Report report:reports) {
            flag = report.equals(report1);
            System.out.println(flag);
            if(flag)
                break;
        }
        Integer delete = 0;
        if(flag){
             delete = reportMapper.delete(reportId);
        }
        if(delete == 1){
            return new ResultVO(201, "success", null);
        }
        return new ResultVO(202, "fail", null);
    }

    @RequestMapping(value = "publishReport", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO publish(Report report,HttpServletRequest request) {
        System.out.println(report);
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        User user = TokenUtil.parseAccessToken(accessToken, refreshToken);
        if (user == null)
            return new ResultVO(201, "no login", null);
        report.setUserId(user.getUserId());
        Date date = new Date();
        report.setPublishTime(date);
        Integer add = reportMapper.add(report);
        ELKUtil.add("report",report);
        if (add == 1)
            return new ResultVO(200, "success", null);
        return new ResultVO(202, "fail", null);
    }


    @RequestMapping(value = "report/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO updateReport(Report report,HttpServletRequest request) {
        System.out.println(report);
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        User user = TokenUtil.parseAccessToken(accessToken, refreshToken);
        if (user == null)
            return new ResultVO(201, "no login", null);
        if(user.getUserId() != report.getUserId())
            return new ResultVO(201, "无操作权限", null);
        Integer update = reportMapper.update(report);
        ELKUtil.add("report",report);
        if (update == 1)
            return new ResultVO(200, "success", null);
        return new ResultVO(202, "fail", null);
    }
}

