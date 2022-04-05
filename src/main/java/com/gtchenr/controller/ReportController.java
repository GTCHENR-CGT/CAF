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

import java.util.List;

@Controller
@RequestMapping("report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Report getReportById(@PathVariable("id") Integer id){

        return reportService.getReportById(id);
    }

    @RequestMapping(value = "reports",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO getReports(){
        return new ResultVO(200,"success",reportService.getReports());
    }
}
