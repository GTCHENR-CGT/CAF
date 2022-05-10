package com.gtchenr.controller;

import com.gtchenr.mapper.CommentMapper;
import com.gtchenr.pojo.Comment;
import com.gtchenr.pojo.User;
import com.gtchenr.pojo.use.Operate;
import com.gtchenr.pojo.Report;
import com.gtchenr.service.ReportService;
import com.gtchenr.service.impl.ReportServiceImpl;
import com.gtchenr.utils.ELKUtil;
import com.gtchenr.utils.MybatisUtil;
import com.gtchenr.utils.TokenUtil;
import com.gtchenr.vo.ResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("report")
public class ReportController {

//    @Autowired
//    ReportService reportService;
    ReportService reportService = new ReportServiceImpl();
    private CommentMapper commentMapper = MybatisUtil.getSqlSession().getMapper(CommentMapper.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getReportById(@PathVariable("id") Integer id) {
        Report report = reportService.getReportById(id);
        if (report == null)
            return new ResultVO(201, "success", null);
        return new ResultVO(200, "success", report);
    }

    @RequestMapping(value = "reports", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getReports() {
        return new ResultVO(200, "success", reportService.getReports());
    }


    @RequestMapping(value = "public", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO add() {

        return null;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO update() {


        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO delete() {

        return null;
    }


    @RequestMapping(value = "getCommentsByReportId", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getCommentsByReportId(Integer reportId) {
        System.out.println("dddddddddd");
        List<Comment> comments = commentMapper.queryCommentByReportId(reportId);
        return new ResultVO(201,"success",comments);
    }

    @RequestMapping(value = "getCommentsByUserId", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getCommentsByUserId(Integer userId) {
        List<Comment> comments = commentMapper.queryCommentByReportId(userId);
        return new ResultVO(201,"success",comments);
    }

    @RequestMapping(value = "like", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO like() {

        return null;
    }


    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO publish(HttpServletRequest request, @Param("report") Report report) {
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        User user = TokenUtil.parseAccessToken(accessToken, refreshToken);
        if (user == null)
            return new ResultVO(201, "no login", null);
        report.setUserId(user.getUserId());
        boolean flag = reportService.add(report);
        ELKUtil.add("report",report);
        if (flag)
            return new ResultVO(200, "success", null);
        return new ResultVO(202, "fail", null);
    }

    @RequestMapping(value = "publish1", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO publish1(@Param("report") Report report) {
        System.out.println("hello");
        boolean flag = reportService.add(report);
        ELKUtil.add("report",report);
        if (flag)
            return new ResultVO(201, "success", null);
        return new ResultVO(202, "fail", null);
    }
    public boolean record(Operate operate) {
        Date date = new Date();
        return true;
    }

    @RequestMapping(value = "queryByReportTitle", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO queryByReportTitle(@Param("report") String reportTitle) {

        return new ResultVO(202, "fail", null);
    }

}
