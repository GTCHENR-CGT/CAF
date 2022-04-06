import apiUrls from "../API/apiUrls.js";
import httpInstance from "../axios/axios.interceptors.js";


export function getReportById(id) {

    return httpInstance.get(apiUrls.getReportById + id);
}