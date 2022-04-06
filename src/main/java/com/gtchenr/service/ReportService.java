package com.gtchenr.service;

import com.gtchenr.mapper.ReportMapper;
import com.gtchenr.pojo.Report;
import com.gtchenr.utils.MybatisUtil;

import java.util.List;

public interface ReportService {


    Report getReportById(Integer id);

    List<Report> getReports();

}
