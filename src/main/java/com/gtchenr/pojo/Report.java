package com.gtchenr.pojo;

public class Report {
    private Integer reportId;
    private Integer userId;
    private Integer boardId;
    private String reportOrganizer;
    private String reportIntroduction;
    private String reportDetails;
    private String reportPeople;

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
}
