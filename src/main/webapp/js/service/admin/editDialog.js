let editDialog = {

    props:{
        dialogvisible:{
            type:Boolean,
            default:false
        }
    },
    data(){
      return {
          dialogVisible: false
      }
    },
    watch:{
        dialogvisible: 'dialogVisibleChange'
    },

    methods:{
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {
                });
        },
        dialogVisibleChange(){
            console.log(this.dialogVisible)
            this.dialogVisible = this.dialogvisible;
        },

        handleCancel(){
            this.dialogVisible = false;
            this.$emit("cancel","editCancel")
        },
        handleConfirm(){
            this.dialogVisible = false;
            this.$emit("confirm","editConfirm")
        }
    },
    template:'                        <el-dialog\n' +
        '                                title="提示"\n' +
        '                                :visible.sync="dialogVisible"\n' +
        '                                width="30%"\n' +
        '                                :before-close="handleClose">\n' +
        '                            <span>这是一段信息</span>\n' +
        '                            <span slot="footer" class="dialog-footer">\n' +
        '                                <el-button @click="handleCancel">取 消</el-button>\n' +
        '                                <el-button type="primary" @click="handleConfirm">确 定</el-button>\n' +
        '                            </span>\n' +
        '                        </el-dialog>'

}

export default editDialog;