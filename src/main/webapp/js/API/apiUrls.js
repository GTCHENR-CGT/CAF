let userPath = "/normalUser";
let recommend = "/recommend";
let report = "/report";
let admin = "/admin";
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
};