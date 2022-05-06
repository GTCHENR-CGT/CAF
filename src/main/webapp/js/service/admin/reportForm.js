let reportForm = {

    props:{
        report:{
            type:Object
        }
    },
    data: function () {
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
                beginDate:'',
                endDate:'',
                endTime:'',
                beginTime:'',
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
                beginDate: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                endDate:[
                    {validator: checkInput, trigger: 'blur'}
                ],
                endTime: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                beginTime: [
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
            }
        };
    },
    methods: {
        submitForm(formName) {
            console.log(this.ruleForm);
            this.$refs[formName].validate((valid) => {
                console.log(this.ruleForm);
                if (valid) {
                    console.log("success submit")
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            console.log(formName)
            this.$refs[formName].resetFields();
        },

        publishReport: function () {
            let data = $('#report').serialize();
            console.log(data);
            publish(data)
                .then((res) => {
                    console.log(res);
                    if(res.statusCode === 201){
                        alert("发布成功，跳转返回主页！")
                        navigateToHome();
                    }else if(res.statusCode === 202){
                        alert("发布失败，请检测输入！或者是否处于登录状态！");
                    }
                })
                .catch((err) => {
                    console.log(err);
                })
        },

        mounted(){
            this.ruleForm = this.report;
        }
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
        '                    <el-col :span="11">\n' +
        '                        <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.beginDate" style="width: 100%;"></el-date-picker>\n' +
        '                    </el-col>\n' +
        '                    <el-col class="line" :span="2">-</el-col>\n' +
        '                    <el-col :span="11">\n' +
        '                        <el-time-picker placeholder="选择时间" v-model="ruleForm.beginTime" style="width: 100%;"></el-time-picker>\n' +
        '                    </el-col>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item label="结束时间">\n' +
        '                    <el-col :span="11">\n' +
        '                        <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.endDate" style="width: 100%; "></el-date-picker>\n' +
        '                    </el-col>\n' +
        '                    <el-col class="line" :span="2">-</el-col>\n' +
        '                    <el-col :span="11">\n' +
        '                        <el-time-picker placeholder="选择时间" v-model="ruleForm.endTime" style="width: 100%"></el-time-picker>\n' +
        '                    </el-col>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item label="报告摘要">\n' +
        '                    <el-input type="textarea" v-model="ruleForm.reportDetails" rows="4"></el-input>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item label="报告人简介">\n' +
        '                    <el-input type="textarea" v-model="ruleForm.reportPeopleInfo" rows="4"></el-input>\n' +
        '                </el-form-item>\n' +
        '                <el-form-item>\n' +
        '                    <el-button type="primary" @click="submitForm(\'ruleForm\')">提交</el-button>\n' +
        '                    <el-button @click="resetForm(\'ruleForm\')">重置</el-button>\n' +
        '                </el-form-item>\n' +
        '            </el-form>'
}

export default reportForm;