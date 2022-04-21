package com.gtchenr.utils;

import com.gtchenr.pojo.Report;
import com.gtchenr.service.ReportService;
import com.gtchenr.service.ReportServiceTest;
import com.gtchenr.service.impl.ReportServiceImpl;
import com.gtchenr.utils.use.ReportELK;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReportELKTest {

    ReportService reportService = new ReportServiceImpl();

    @Test
    public void Test() {
        List<Report> reports = new ArrayList<>();
        Report reportById = reportService.getReportById(1);
        reports.add(reportById);
        Report reportById2 = reportService.getReportById(2);
        System.out.println(reportById);
        reports.add(reportById2);
        ReportELK.add(reports);
    }

    @Test
    public void addTest() {
        Report reportById = reportService.getReportById(3);
        List<Report> reports = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            reports.add(reportById);
        }
        ReportELK.add(reports);
    }

    @Test
    public void queryTest() {
        System.out.println(ReportELK.queryByReportId(2));
    }
}
