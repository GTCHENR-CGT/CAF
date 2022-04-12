package com.gtchenr.utils.use;

import com.alibaba.fastjson.JSON;
import com.gtchenr.pojo.Report;
import com.gtchenr.utils.ELKUtil;

import java.util.List;

public class ReportELK {

    private final static String INDEX = "report";
    private final static Class<Report> REPORT_CLASS = Report.class;


    public static void add(List<Report> reports){
        for (Report report:reports) {
            ELKUtil.add(INDEX,report);
        }
    }

    public static void add(Report report){
        ELKUtil.add(INDEX,report);
    }

    public static Report get(String docId){
        String s = ELKUtil.get(INDEX, docId);
        Report report = JSON.parseObject(s, REPORT_CLASS);
        return report;
    }

    public static void getAll(){

    }

    public static void queryByReportId(Integer reportId){

    }

    public static void queryByReportTitle(String reportTitle){

    }

    public static void queryByKeywords(String keyword){

    }

    public static void queryByReportPeople(String reportPeople){

    }

    public static void queryByReportDetails(String reportDetails){

    }

    public static void queryByReportPeopleInfo(String reportPeopleInfo){

    }
}
