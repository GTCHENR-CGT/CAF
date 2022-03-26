import pageUrls from "./pageUrls.js";
import {navigateTo} from "./common.js";

/**
 * 跳转到主页面
 * @returns {*}
 */
export function navigateToHome(){

    return navigateTo(pageUrls.home);
}