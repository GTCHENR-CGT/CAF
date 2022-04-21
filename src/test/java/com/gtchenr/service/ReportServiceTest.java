package com.gtchenr.service;

import com.gtchenr.pojo.Report;
import com.gtchenr.service.impl.ReportServiceImpl;
import org.junit.Test;

public class ReportServiceTest {

    ReportService reportService = new ReportServiceImpl();

    @Test
    public void addTest() {
        Report report = new Report();
        report.setReportDetails("helloo");
        report.setReportLocation("ddddddddd");
        report.setUserId(1);
        System.out.println(report);
        reportService.add(report);
    }

    @Test
    public void addTest02() {
        Report report = new Report();
        report.setReportTitle("dss");
        report.setReportPeople("dddd");
        report.setReportLocation("sddddddddd");
        report.setReportOrganizer("dddddd");
        report.setReportDetails("dddddddsssssssss");
        reportService.add(report);
    }
}
