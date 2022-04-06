package com.gtchenr.service.impl;

import com.gtchenr.mapper.ReportMapper;
import com.gtchenr.pojo.Report;
import com.gtchenr.service.ReportService;
import com.gtchenr.utils.MybatisUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    ReportMapper reportMapper = MybatisUtil.getSqlSession().getMapper(ReportMapper.class);

    @Override
    public Report getReportById(Integer id) {
        return reportMapper.queryReportById(id);
    }

    @Override
    public List<Report> getReports() {
        return reportMapper.queryReportsInfo();
    }
}
