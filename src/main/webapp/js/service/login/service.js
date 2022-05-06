import apiUrls from "../../API/apiUrls.js";
import httpInstance from "../../axios/axios.interceptors.js";

export function login(data) {
    return httpInstance.post(apiUrls.loginUrl, data);
}

export function authorization() {
    return httpInstance.post(apiUrls.authorizationUrl);
}

export function adminLogin(data){
    return httpInstance.post(apiUrls.adminLogin, data);
}
