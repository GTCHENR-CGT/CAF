package com.gtchenr.controller;

import com.gtchenr.pojo.Report;
import com.gtchenr.service.ReportService;
import com.gtchenr.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getReportById(@PathVariable("id") Integer id){
        Report report = reportService.getReportById(id);
        if(report == null)
            return new ResultVO(201,"success",null);
        return new ResultVO(200,"success",report);
    }

    @RequestMapping(value = "reports",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getReports(){
        return new ResultVO(200,"success",reportService.getReports());
    }
}
