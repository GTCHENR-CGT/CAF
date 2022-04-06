package com.gtchenr.mapper;

import com.gtchenr.pojo.Report;
import com.gtchenr.utils.MybatisUtil;
import org.junit.Test;

import java.util.List;

public class ReportMapperTest {

    ReportMapper reportMapper = MybatisUtil.getSqlSession().getMapper(ReportMapper.class);
    
    @Test
    public void queryAllTest(){
        Report report = new Report();
        List<Report> reports = reportMapper.queryReports();
        for (Report r:reports) {
            System.out.println(r);
        }
    }

    @Test
    public void  queryReportByIdTest(){
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
}
