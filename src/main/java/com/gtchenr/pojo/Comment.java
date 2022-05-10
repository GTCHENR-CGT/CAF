package com.gtchenr.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class Comment {
    public Comment() {
    }

    private Integer commentId;
    private Integer reportId;
    private Integer userId;
    private String commentDetails;
    private Integer commentCredit;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime;

    public Integer getCommentCredit() {
        return commentCredit;
    }

    public void setCommentCredit(Integer commentCredit) {
        this.commentCredit = commentCredit;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }

    public Comment(Integer commentId, Integer reportId, Integer userId, String commentDetails, Integer commentCredit, Date publishTime) {
        this.commentId = commentId;
        this.reportId = reportId;
        this.userId = userId;
        this.commentDetails = commentDetails;
        this.commentCredit = commentCredit;
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", reportId=" + reportId +
                ", userId=" + userId +
                ", commentDetails='" + commentDetails + '\'' +
                ", commentCredit=" + commentCredit +
                ", publishTime=" + publishTime +
                '}';
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }
}
