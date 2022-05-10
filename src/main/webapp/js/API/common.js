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