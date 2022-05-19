import {getReportById,updateReport} from "../service.js";
import {navigateToPersonalHome, navigateToReport} from "../../API/pageTurn.js";

let reportForm = {
    props:{
        reporttt:0,
    },
    data() {
        var checkInput = (rule, value, callback) => {
            switch (rule.field) {
                case 'reportTitle':
                    if (!value) {
                        return callback(new Error('报告题目不能为空'));
                    }
                    break;
                case 'reportPeople':
                    if (!value) {
                        return callback(new Error('报告人不能为空'));
                    }
                    break;
                case 'reportOrganizer':
                    if (!value) {
                        return callback(new Error('组织者不能为空'));
                    }
                    break;
                case 'reportLocation':
                    if (!value) {
                        return callback(new Error('地点不能为空'));
                    }
                    break;
                case 'reportDetails':
                    if (!value) {
                        return callback(new Error('报告摘要不能为空'));
                    }
                    break;
                case 'reportPeopleInfo':
                    if (!value) {
                        return callback(new Error('报告人简介不能为空'));
                    }
                    break;
            }
            // setTimeout(() => {
            //     if (!Number.isInteger(value)) {
            //         callback(new Error('请输入数字值'));
            //     }
            // }, 1000);
        };
        return {
            isLogin:true,
            ruleForm: {
                reportTitle:'',
                reportPeople: '',
                reportOrganizer: '',
                reportLocation: '',
                reportEndTime:'',
                reportBeginTime:'',
                reportDetails:'',
                reportPeopleInfo:''
            },
            rules: {
                reportTitle: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                reportPeople: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                reportOrganizer: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                reportEndTime: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                reportBeginTime: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                reportLocation: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                reportDetails: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                reportPeopleInfo: [
                    {validator: checkInput, trigger: 'blur'}
                ],
            },
            report:{}
        };
    },
    methods: {
        submitForm() {
            console.log("jjjjjjjjjjjjkkkkkkkkkkkkkkkkk")
            console.log(this.ruleForm);
            this.updateReport1()

        },
        cancelForm(formName) {
            console.log("jjjjjjjjjjjjkkkkkkkkkkkkkkkkk")
        },

        updateReport1() {
            let data = {
                'reportName':this.ruleForm.reportName,
                'reportLocation':this.ruleForm.reportLocation,
                'reportOrganizer':this.ruleForm.reportOrganizer,
                'reportPeople':this.ruleForm.reportPeople,
                'reportDetails':this.ruleForm.reportDetails,
                'reportPeopleInfo':this.ruleForm.reportPeopleInfo,
                'reportBeginTime':this.ruleForm.reportBeginTime,
                'reportEndTime':this.ruleForm.reportEndTime,
                'reportId':this.report.reportId,
                'userId':this.report.userId
            };
            console.log(data);
           updateReport(data)
                .then((res) => {
                    console.log(res);
                    if(res.statusCode === 200){
                        alert("发布成功，跳转返回主页！")
                        navigateToReport(this.report.reportId);
                    }else if(res.statusCode === 202 || res.statusCode === 201){
                        alert("发布失败，请检测输入！或者是否处于登录状态！");
                    }
                })
                .catch((err) => {
                    console.log(err);
                })
        },
    },
    mounted(){
        console.log(this.reporttt);
        getReportById(this.reporttt)
            .then((res)=>{
                this.report = res.data;
                this.ruleForm = this.report;
                console.log(this.ruleForm)
            })
            .catch(err => console.log(err))

    },
    template:'            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"\n' +
        '                     class="demo-ruleForm">\n' +
        '                <el-form-item label="报告题目" prop="reportTitle" >\n' +
        '                    <el-input v-model="ruleForm.reportTitle"></el-input>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item label="报告人" prop="reportPeople">\n' +
        '                    <el-input v-model="ruleForm.reportPeople"></el-input>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item label="组织者" prop="reportOrganizer">\n' +
        '                    <el-input v-model="ruleForm.reportOrganizer"></el-input>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item label="地点" prop="reportLocation">\n' +
        '                    <el-input v-model="ruleForm.reportLocation"></el-input>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item label="开始时间">\n' +
        '                    <el-date-picker v-model="ruleForm.reportBeginTime" type="datetime" placeholder="选择日期时间" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item label="结束时间">\n' +
        '                    <el-date-picker v-model="ruleForm.reportEndTime" type="datetime" placeholder="选择日期时间" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>\n' +
        '                </el-form-item>\n'+
        '                <el-form-item label="报告摘要">\n' +
        '                    <el-input type="textarea" v-model="ruleForm.reportDetails" rows="4"></el-input>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item label="报告人简介">\n' +
        '                    <el-input type="textarea" v-model="ruleForm.reportPeopleInfo" rows="4"></el-input>\n' +
        '                </el-form-item>\n' +
        '            </el-form>'
}

export default reportForm;