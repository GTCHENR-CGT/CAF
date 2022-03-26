package com.gtchenr.pojo;

import java.util.Date;

public class OperateRecord {

    private Integer operateId;
    private Integer OperateName;
    private Integer reportId;
    private Integer userId;
    private Date operateDate;

    public Integer getOperateId() {
        return operateId;
    }

    public void setOperateId(Integer operateId) {
        this.operateId = operateId;
    }

    public Integer getOperateName() {
        return OperateName;
    }

    public void setOperateName(Integer operateName) {
        OperateName = operateName;
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

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
}
