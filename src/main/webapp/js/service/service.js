import apiUrls from "../API/apiUrls.js";
import httpInstance from "../axios/axios.interceptors.js";

export function getReportById(id) {

    return httpInstance.get(apiUrls.getReportById + id);
}

export function publish(data) {
    return httpInstance.post(apiUrls.publishReport, data);
}

export function publish1(data) {
    return httpInstance.post(apiUrls.publishReport1, data);
}

export function login(data) {
    return httpInstance.post(apiUrls.loginUrl, data);
}

export function authorization() {
    return httpInstance.post(apiUrls.authorizationUrl);
}

export function getReportUrlByNewest() {
    return httpInstance.get(apiUrls.getReportUrlByNewest);
}

export function getReports() {
    return httpInstance.get(apiUrls.getReports);
}

export function deleteReportByReportId(reportId){
    return httpInstance.delete(apiUrls.deleteReport+"/"+reportId);
}


