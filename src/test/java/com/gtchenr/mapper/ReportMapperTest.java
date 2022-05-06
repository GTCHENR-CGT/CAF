package com.gtchenr.mapper;

import com.gtchenr.pojo.Report;
import com.gtchenr.utils.MybatisUtil;
import org.junit.Test;

import java.util.List;

public class ReportMapperTest {

    ReportMapper reportMapper = MybatisUtil.getSqlSession().getMapper(ReportMapper.class);

    @Test
    public void queryAllTest() {
        Report report = new Report();
        List<Report> reports = reportMapper.queryReports();
        for (Report r : reports) {
            System.out.println(r);
        }
    }

    @Test
    public void queryReportByIdTest() {
        Report report = reportMapper.queryReportInfoById(1);
        System.out.println(report);
    }

    @Test
    public void queryReportById() {

        Report report = reportMapper.queryReportInfoById(1);
        System.out.println(report);
    }

    @Test
    public void queryReportInfoTest() {

        List<Report> reports = reportMapper.queryReportsInfo();
        for (Report r : reports) {
            System.out.println(r);
        }
    }

    @Test
    public void addTest() {
        Report report = new Report();
        report.setUserId(1);
        Integer add = reportMapper.add(report);
        System.out.println(add);
    }

    @Test
    public void deleteTest(){
        System.out.println(reportMapper.delete(67));;
    }

    @Test
    public void updateTest(){

        Report report = new Report();
        report.setReportId(68);
        report.setUserId(1);
        report.setReportTitle("aa");
        System.out.println(reportMapper.update(report));
    }
}
