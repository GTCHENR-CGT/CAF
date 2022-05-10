package com.gtchenr.controller;

import com.alibaba.fastjson.JSON;
import com.gtchenr.pojo.User;
import com.gtchenr.pojo.use.Operate;
import com.gtchenr.pojo.Report;
import com.gtchenr.service.ReportService;
import com.gtchenr.service.impl.ReportServiceImpl;
import com.gtchenr.utils.ELKUtil;
import com.gtchenr.utils.TokenUtil;
import com.gtchenr.vo.ResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("search")
public class QueryController {

    @RequestMapping(value = "/reportTitle", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getReportByReportTitle(String keyword) {
        try {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            keyword = URLDecoder.decode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<String> list = ELKUtil.queryByMulti("report", 1, 10, keyword, "reportTitle");
        try {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            keyword = URLDecoder.decode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Report> reports = new ArrayList<>();
        for (String report :list) {
            reports.add(JSON.parseObject(report,Report.class));
        }
        return new ResultVO(201, "success", reports);
//        return new ResultVO(200, "success", report);
    }

    @RequestMapping(value = "/reportPeople", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getReportByReportPeople(String keyword) {
        try {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            keyword = URLDecoder.decode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<String> list = ELKUtil.queryByMulti("report", 1, 10, keyword, "reportPeople");
        List<Report> reports = new ArrayList<>();
        for (String report :list) {
            reports.add(JSON.parseObject(report,Report.class));
        }
        return new ResultVO(201, "success", reports);
//        return new ResultVO(200, "success", report);
    }

    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getReportByKeyword(String keyword) {
        try {
            keyword = URLDecoder.decode(keyword, "UTF-8");
            keyword = URLDecoder.decode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<String> list = ELKUtil.queryByMulti("report", 1, 10, keyword, "reportTitle","reportPeople","reportDetails","reportPeopleInfo","reportOrganizer");

        List<Report> reports = new ArrayList<>();
        for (String report :list) {
            reports.add(JSON.parseObject(report,Report.class));
        }
        for (Report report :reports) {
            System.out.println(report.getPublishTime());
        }
        return new ResultVO(201, "success", reports);
    }


}
