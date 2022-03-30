import {getReportUrlByNewest} from "../js/service/home/service.js";
window.vm = new Vue({
    el:"#report-list",
    data:{
        reportList:["",""]
    },
    method:{

    },
    onload:function (){
        getReportUrlByNewest()
            .then((res)=>{
                console.log(res);
            },(err)=>{
                console.log(err);
            })
    }
})