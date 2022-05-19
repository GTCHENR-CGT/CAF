import {getUserInfo} from "../service.js";
import {checkSex,parseSex} from "../../API/common.js";

export let editUserDialog = {
    props: {
        dialogvisible1:false,
    },
    data() {
        var checkInput = (rule, value, callback) => {
            switch (rule.field) {
                case 'loginName':
                    if (!value) {
                        return callback(new Error('用户名不能为空'));
                    }
                    break;
                case 'name':
                    if (!value) {
                        return callback(new Error('昵称不能为空'));
                    }
                    break;
                case 'sex':
                    if (!value) {
                        return callback(new Error('未选择性别'));
                    }
                    break;
            }
        };
        return {
            ruleForm: {
                loginName: '',
                name: '',
                pass: '',
                checkPass: '',
                sex: Number,
                phone: '',
                email: '',
                password: '',
            },
            rules: {
                name: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                sex: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                email: [
                    {validator: checkInput, trigger: 'blur'}
                ],
                phone: [
                    {validator: checkInput, trigger: 'blur'}
                ]
            },
            dialogVisible:false
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                console.log(this.ruleForm);
                if (valid) {
                    console.log("success submit")
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
            this.$emit("edituser",{'user':this.ruleForm});
        },
        resetForm(formName) {
            this.$emit("cancel");
        },
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
        }

    },

    mounted() {
        getUserInfo()
            .then(res=>{
                this.ruleForm.name = res.data.name;
                this.ruleForm.sex = checkSex(res.data.sex);
                this.ruleForm.phone = res.data.phone;
                this.ruleForm.email = res.data.email;
                console.log("================")
                console.log(this.ruleForm);
            })
            .catch(err=>{
                console.log("================")
                console.log(err)
            })
    },

    watch: {
        'dialogvisible1': {
            handler(newVal, oldVal) {
                this.dialogVisible = newVal;
            },
            deep: true,  // 深度监听
            immediate: true,
        }
    },
    template: '    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">\n' +
        '        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"\n' +
        '                 class="demo-ruleForm">\n' +
        '            <el-form-item label="昵称" prop="name">\n' +
        '                <el-input v-model="ruleForm.name"></el-input>\n' +
        '            </el-form-item>\n' +
        '            <el-form-item label="性别">\n' +
        '                <el-radio-group v-model="ruleForm.sex">\n' +
        '                    <el-radio label="男"></el-radio>\n' +
        '                    <el-radio label="女"></el-radio>\n' +
        '                    <el-radio label="保密~"></el-radio>\n' +
        '                </el-radio-group>\n' +
        '            </el-form-item>\n' +
        '            <el-form-item label="电话" prop="phone">\n' +
        '                <el-input v-model="ruleForm.phone"></el-input>\n' +
        '            </el-form-item>\n' +
        '            <el-form-item label="邮箱" prop="email">\n' +
        '                <el-input v-model="ruleForm.email"></el-input>\n' +
        '            </el-form-item>\n' +
        '        </el-form>\n' +
        '        <span slot="footer" class="dialog-footer">\n' +
        '            <el-button type="primary" @click="submitForm(\'ruleForm\')">修改</el-button>\n' +
        '            <el-button @click="resetForm(\'ruleForm\')">取消</el-button>\n' +
        '        </span>\n' +
        '    </el-dialog>'
}