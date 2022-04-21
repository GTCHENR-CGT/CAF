import apiUrls from "../../API/apiUrls.js";
import httpInstance from "../../axios/axios.interceptors.js";

export function publish(data) {
    return httpInstance.post(apiUrls.publishReport, data);
}

export function publish1(data) {
    return httpInstance.post(apiUrls.publishReport1, data);
}

