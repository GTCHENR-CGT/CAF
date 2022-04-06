const config = {
    // the mothods of this request
    method: "get", // default
    // the base url will unshift into the url you write in the parmas
    // baseURL: "http://localhost:8080",
    baseURL: "http://10.21.128.21:8080",
    // baseURL: "http://192.168.43.166:8080",
    timeout: 5000,
    // when cross origin,whether the request will cookie
    withCredentials: false, // default
    // type of the reponse data
    reponseType: "json",
};

export default config; 

