import {
    navigateToHome,
    navigateToAdminLogin,
    navigateToPersonalHome,
    navigateToRegister,
    navigateToLogin1
} from "../../js/API/pageTurn.js"
import {getUserInfo,authorization} from "../../js/service/service.js"
import {getStorage} from "../../js/API/common.js"

let borderItem = {
    props: {
        name: String,
        select: String
    },

    template: '<div class="letallCenter click title_item" :class="select">{{name}}</div>'
}

export let head = {
    components: {
        'border-item': borderItem,
    },
    data() {
        return {
            isLogin: false,
            borders: [{name: "论坛首页", select: ""},
                {name: "热度推荐", select: ""},
                {name: "最新发布", select: ""},
                {name: "最新回复", select: ""},
                {name: "计算机", select: ""},
                {name: "数学", select: ""}],
            borderSelected: "数学",
            userInfo: {}
        }
    },
    methods: {
        borderClick: function (border) {
            this.borderSelected = border.name;
            for (let i = 0; i < this.borders.length; i++) {
                if (border.name === this.borders[i].name) {
                    this.borders[i].select = "title_item_select";
                } else {
                    this.borders[i].select = "";
                }
            }
            navigateToHome();
        },
        navigateToAdminLogin1() {
            console.log("navigateToAdminLogin1");
            navigateToAdminLogin();
        },
        navigateToPersonalHome1() {
            navigateToPersonalHome();
        },
        navigateToLogin2(){
            navigateToLogin1()
        },
        navigateToRegister1(){
            navigateToRegister();
        }
    },
    mounted() {
        getUserInfo({'userId': parseInt(getStorage("userId"))})
            .then(res => {
                this.userInfo = res.data;
            })
            .catch(err => {
                console.log(err)
            })
        authorization()
            .then(res=>{
                if (res.data){
                    this.isLogin = true;
                }else {
                    this.isLogin =false;
                }
            })
    },
    template: '<div>    <div class="user_info flex-row-reverse" v-if="!isLogin" style="background-color: rgb(246,246,246)">\n' +
        '        <div class="user_item">\n' +
        '            <label>欢迎使用校园学术论坛，请先<label class="click" @click="navigateToLogin2" style="color: blue">登录</label>或者<label @click="navigateToRegister1" style="color: blue" class="click">注册</label></label>\n' +
        '            <label class="admin click" @click="navigateToAdminLogin1()" style="color: blue">管理员登录</label>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <div class="user_info flex-row-reverse" style="background-color: rgb(246,246,246)" v-if="isLogin">\n' +
        '        <div class="user_item">\n' +
        '            <!--            <label>欢迎校园学术论坛</label>-->\n' +
        '            <label>你好，{{userInfo.name}}!</label>\n' +
        '            <label class="click" style="color: blue" @click="navigateToPersonalHome1()">我的首页</label>\n' +
        '            <label class="click" style="color: blue">消息</label>\n' +
        '            <label class="admin click" style="color: blue" @click="navigateToAdminLogin1()"><a>管理员登录</a></label>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <div class="title flex-row letallCenter" id="border">\n' +
        '        <div class="title_item letallCenter">学术论坛</div>\n' +
        '\n' +
        '        <border-item v-for="border in borders" :name="border.name" :select="border.select"\n' +
        '                     @click.native="borderClick(border)"></border-item>\n' +
        '    </div></div>'
};
