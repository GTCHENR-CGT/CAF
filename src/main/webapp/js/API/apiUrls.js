let userPath = "/normalUser";
let recommend = "/recommend";
let report = "/report"
export default {
    loginUrl: userPath + "/login",
    authorizationUrl: userPath + "/authorization",
    getReportUrlByNewest: recommend + "/newest",
    getReports: report + "/reports",
    getReportById: report + "/",
    publishReport: report + "/publish",
    publishReport1: report+ "/publish1"
};