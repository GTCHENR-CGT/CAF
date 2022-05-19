import reportForm from "./reportForm.js";

export let editDialog = {

    components: {
        "report-form": reportForm,
    },
    props: {
        reportt: 0,
        dialogvisible1: false
    },
    data() {
        return {
            dialogVisible: false,
            report: {}
        }
    },
    watch: {
        'dialogvisible1': {
            handler(newVal, oldVal) {
                this.dialogVisible = newVal;
                console.log("=====================")
            },
            deep: true,  // 深度监听
            immediate: true,
        },
    },

    methods: {
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {
                });
        },

        handleCancel() {
            this.dialogVisible = false;
            this.$refs.reportForm.cancelForm();
        },
        handleConfirm() {
            this.dialogVisible = false;
            this.$refs.reportForm.submitForm();
        },

    },
    mounted() {
        console.log("===========")
        console.log(this.dialogvisible1);
        console.log(this.reportt);
    },
    template: '                        <el-dialog\n' +
        '                                title="报告信息"\n' +
        '                                :visible.sync="dialogVisible"\n' +
        '                                width="30%"\n' +
        '                                :before-close="handleClose">\n' +
        '                            <report-form :reporttt="reportt" ref="reportForm"></report-form>\n' +
        '                            <span slot="footer" class="dialog-footer">\n' +
        '                                <el-button @click="handleCancel">取 消</el-button>\n' +
        '                                <el-button type="primary" @click="handleConfirm">修改</el-button>\n' +
        '                            </span>\n' +
        '                        </el-dialog>'

}
