import apiUrls from "../API/apiUrls.js";
import httpInstance from "../axios/axios.interceptors.js";

export async function getReportById(id) {

    return httpInstance.get(apiUrls.getReportById + id);
}

export async function publish(data) {
    return httpInstance.post(apiUrls.publishReport, data);
}

export async function publish1(data) {
    return httpInstance.post(apiUrls.publishReport1, data);
}

export async function login(data) {
    return httpInstance.post(apiUrls.loginUrl, data);
}

export async function authorization() {
    return httpInstance.post(apiUrls.authorizationUrl);
}

export async function getReportUrlByNewest() {
    return httpInstance.get(apiUrls.getReportUrlByNewest);
}

export async function getReports() {
    return httpInstance.get(apiUrls.getReports);
}

export async function deleteReportByReportId(reportId){
    return httpInstance.delete(apiUrls.deleteReport+"/"+reportId);
}

export async function reports(){

    return httpInstance.get(apiUrls.adminReports);
}

export async function searchByReportTitle(keyword){

    return httpInstance.get(apiUrls.searchByReportTitle,keyword);
}

export async function searchByKeyword(keyword){

    return httpInstance.get(apiUrls.searchByKeyword,keyword);
}

export async function searchByReportPeople(keyword){

    return httpInstance.get(apiUrls.searchByReportPeople,keyword);
}

export async function getUserInfo(data){

    return httpInstance.get(apiUrls.getUserInfo,data);
}

export async function getCommentsByUserId(data){

    return httpInstance.get(apiUrls.getCommentsByUserId,data);
}

export async function getCommentsByReportId(data){

    return httpInstance.get(apiUrls.getCommentsByReportId,data);
}

export async function publishComment(data){

    return httpInstance.post(apiUrls.publishComment,data);
}

export async function getReportByUserId(){
    return httpInstance.get(apiUrls.getReportByUserId);
}