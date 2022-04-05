import {getReportUrlByNewest} from "../js/service/home/service.js";

let vm = new Vue({
    el: "#home",
    data: {
        reportList: ["", ""]
    },
    method: {},
    onload: function () {
        getReportUrlByNewest()
            .then((res) => {
                console.log(res);
            }, (err) => {
                console.log(err);
            })
    }
})