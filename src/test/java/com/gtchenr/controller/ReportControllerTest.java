package com.gtchenr.controller;

import com.gtchenr.pojo.Report;
import org.junit.Test;

public class ReportControllerTest {

    ReportController reportController = new ReportController();
    @Test
    public void publishTest(){
        Report report = new Report();
        report.setReportTitle("dss");
        report.setReportPeople("dddd");
        report.setReportLocation("sddddddddd");
        report.setReportOrganizer("dddddd");
        report.setReportDetails("dddddddsssssssss");
        reportController.publish1(report);
    }
}
