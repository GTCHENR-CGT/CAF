import {getReportUrlByNewest} from "../../";
import Vue from "../../lib/vue"

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


let name = "gtchenr";