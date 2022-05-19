export function setStorage(key, data) {
    try {
        window.localStorage.setItem(key, data);
    } catch (e) {
        console.error(e);
    }
}

export function getStorage(key) {
    try {
        let data = window.localStorage.getItem(key);
        return data;
    } catch (e) {
        console.log(e);
    }
}

export function navigateTo(url, params) {
    $(window).attr('location', url)
}

export function setSessionStorage(key, data) {
    sessionStorage.setItem(key, data);
}

export function getSessionStorage(key) {

    return sessionStorage.getItem(key);
}

export function getQueryVariable(variable) {
    let query = window.location.search.substring(1);
    let vars = query.split("&");
    for (let i = 0; i < vars.length; i++) {
        let pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return false;
}

export function parseSex(sex){
    if(sex === '男')
        return 1;
    else if(sex === '女')
        return 2;
    else if(sex === '保密~')
        return 3
}
export function checkSex(sex){
    if(sex === 1)
        return '男';
    else if(sex === 2)
        return '女';
    else if(sex === 3)
        return '保密~';
}