package com.gtchenr.service.impl;

import com.gtchenr.mapper.ReportMapper;
import com.gtchenr.pojo.Report;
import com.gtchenr.service.ReportService;
import com.gtchenr.utils.MybatisUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    ReportMapper reportMapper = MybatisUtil.getSqlSession1().getMapper(ReportMapper.class);

    @Override
    public Report getReportById(Integer id) {
        return reportMapper.queryReportById(id);
    }

    @Override
    public List<Report> getReports() {
//        return reportMapper.queryReportsInfo();
        return reportMapper.queryReports();
    }

    @Override
    public boolean add(Report report) {
        if (report == null)
            return false;
        System.out.println(report);
        Integer add = reportMapper.add(report);
        System.out.println(add);
        if (add == 1) {
            MybatisUtil.getSqlSession().commit();
            System.out.println("完成");
            return true;
        }
        return false;
    }

    @Override
    public List<Report> reports() {
        return reportMapper.queryReports();
    }

    @Override
    public Integer delete(Integer id) {
        return reportMapper.delete(id);
    }

    @Override
    public Integer update(Report report) {
        return reportMapper.update(report);
    }


}
