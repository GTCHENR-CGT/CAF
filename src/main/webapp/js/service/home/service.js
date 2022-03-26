import apiUrls from "../../API/apiUrls.js";
import httpInstance from "../../axios/axios.interceptors.js";

/**
 * 跳转到主页面
 * @returns {*}
 */
export function navigateToHome(){
    return httpInstance.get(apiUrls.navigateToHomeUrl);
}