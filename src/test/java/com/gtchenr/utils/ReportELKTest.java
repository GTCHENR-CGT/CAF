package com.gtchenr.utils;

import com.gtchenr.pojo.Report;
import com.gtchenr.service.ReportService;
import com.gtchenr.service.impl.ReportServiceImpl;
import com.gtchenr.utils.use.ReportELK;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReportELKTest {

    ReportService reportService = new ReportServiceImpl();
    @Test
    public void Test(){
        List<Report> reports = new ArrayList<>();
        Report reportById = reportService.getReportById(1);
        reports.add(reportById);
        Report reportById2 = reportService.getReportById(2);
        System.out.println(reportById);
        reports.add(reportById2);
        ReportELK.add(reports);
    }
}
