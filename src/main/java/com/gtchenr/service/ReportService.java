package com.gtchenr.service;

import com.gtchenr.mapper.ReportMapper;
import com.gtchenr.pojo.Report;
import com.gtchenr.utils.MybatisUtil;

import java.util.List;

public interface ReportService {


    Report getReportById(Integer id);

    List<Report> getReports();

    boolean add(Report report);

    List<Report> reports();

    Integer delete(Integer id);

    Integer update(Report report);
}
