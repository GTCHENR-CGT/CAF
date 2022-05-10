package com.gtchenr.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Report {
    private Integer reportId;
    private Integer userId;
    private Integer boardId;
    private String reportOrganizer;
    private String reportIntroduction;
    private String reportDetails;
    private String reportPeople;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reportBeginTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reportEndTime;
    private String reportLocation;
    private String reportPeopleInfo;
    private String reportTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime;

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }


    public Date getReportBeginTime() {
        return reportBeginTime;
    }

    public void setReportBeginTime(Date reportBeginTime) {
        this.reportBeginTime = reportBeginTime;
    }

    public Date getReportEndTime() {
        return reportEndTime;
    }

    public void setReportEndTime(Date reportEndTime) {
        this.reportEndTime = reportEndTime;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public String getReportPeopleInfo() {
        return reportPeopleInfo;
    }

    public void setReportPeopleInfo(String reportPeopleInfo) {
        this.reportPeopleInfo = reportPeopleInfo;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public String getReportOrganizer() {
        return reportOrganizer;
    }

    public void setReportOrganizer(String reportOrganizer) {
        this.reportOrganizer = reportOrganizer;
    }

    public String getReportIntroduction() {
        return reportIntroduction;
    }

    public void setReportIntroduction(String reportIntroduction) {
        this.reportIntroduction = reportIntroduction;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public String getReportPeople() {
        return reportPeople;
    }

    public void setReportPeople(String reportPeople) {
        this.reportPeople = reportPeople;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", userId=" + userId +
                ", boardId=" + boardId +
                ", reportOrganizer='" + reportOrganizer + '\'' +
                ", reportIntroduction='" + reportIntroduction + '\'' +
                ", reportDetails='" + reportDetails + '\'' +
                ", reportPeople='" + reportPeople + '\'' +
                ", reportBeginTime=" + reportBeginTime +
                ", reportEndTime=" + reportEndTime +
                ", reportLocation='" + reportLocation + '\'' +
                ", reportPeopleInfo='" + reportPeopleInfo + '\'' +
                ", reportTitle='" + reportTitle + '\'' +
                '}';
    }
}
