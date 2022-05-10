let userPath = "/normalUser";
let recommend = "/recommend";
let report = "/report";
let admin = "/admin";
let search = "/search";
export default {
    loginUrl: userPath + "/login",
    authorizationUrl: userPath + "/authorization",
    getReportUrlByNewest: recommend + "/newest",
    getReports: report + "/reports",
    getReportById: report + "/",
    publishReport: report + "/publish",
    publishReport1: report+ "/publish1",
    deleteReport: admin + "/report/delete",
    adminLogin: admin+"/login",
    adminReports:admin+"/reports",
    searchByReportTitle:search+"/reportTitle",
    searchByKeyword:search+"/keyword",
    searchByReportPeople:search+"/reportPeople",
    getUserInfo:userPath+"/userInfo",
    getCommentsByReportId:report+"/getCommentsByReportId",
    getCommentsByUserId:userPath+"/getCommentsByUserId",
    publishComment:userPath+"/publishComment",
    getReportByUserId:userPath+"/getReportByUserId"
};