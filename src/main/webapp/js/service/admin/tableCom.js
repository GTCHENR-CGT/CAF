
const tableCom = {
    props: {
        data: Array,
        type:String,
        operate:{
            type:String,
            default: 'view'
        }
    },
    data() {
        return {
            multipleSelection: [],
            tableType:["user","report"],
            rows:[],
            operateSelect:['view','delete'],
            reportRows:[
                {
                    label:"报告id",
                    width:"100",
                    prop:"reportId",
                    showOverflowTooltip:false
                },
                {
                    label: "用户id",
                    width:"100",
                    prop:"userId",
                    showOverflowTooltip:false
                },
                {
                    label: "版块id",
                    width:"100",
                    prop:"boardId",
                    showOverflowTooltip:false
                },
                {
                    label: "报告题目",
                    width:"200",
                    prop:"reportTitle",
                    showOverflowTooltip:true
                },
                {
                    label: "汇报人",
                    width:"220",
                    prop:"reportPeople",
                    showOverflowTooltip:true
                },
                {
                    label: "组织者",
                    width:"220",
                    prop:"reportOrganizer",
                    showOverflowTooltip:true
                },
                {
                    label: "汇报简介",
                    width:"280",
                    prop:"reportDetails",
                    showOverflowTooltip:true
                }
            ],
            userRows:[
                {
                    label:"用户id",
                    width:"",
                    prop:"",
                    showOverflowTooltip:false
                },
                {
                    label: "登录名",
                    width:"",
                    prop:"",
                    showOverflowTooltip:false
                },
                {
                    label: "角色id",
                    width:"",
                    prop:"",
                    showOverflowTooltip:false
                },
                {
                    label: "姓名",
                    width:"",
                    prop:"",
                    showOverflowTooltip:false
                },
                {
                    label: "性别",
                    width:"",
                    prop:"",
                    showOverflowTooltip:false
                },
                {
                    label: "电话",
                    width:"",
                    prop:"",
                    showOverflowTooltip:false
                },
                {
                    label: "邮箱",
                    width:"",
                    prop:"",
                    showOverflowTooltip:false
                }
            ]
        };
    },
    created: function () {
        console.log(this.operateSelect)
        if(this.type===this.tableType[0]){
            this.rows =this.userRows;
        }else if(this.type===this.tableType[1])
            this.rows = this.reportRows;
    },
    methods: {
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },

        handleClick(row){
            console.log(row);

        },
        handleEdit(index, rows){
            console.log('handleEdit')
            this.$emit("handleedit",{index:index,rows:rows});
        },
        handleDelete:function (index, rows) {

            this.$emit("handledelete",{index:index,rows:rows});
        }

    },
    template: '               <el-table ref="multipleTable" :data="data" tooltip-effect="dark" style="width: 100%"\n' +
        '                              @selection-change="handleSelectionChange">\n' +
        '                        <el-table-column type="selection" width="55"></el-table-column>\n' +
        '                        <template v-for="row in rows">\n' +
        '                            <el-table-column prop="name" :label="row.label" :width="row.width" :prop="row.prop" :show-overflow-tooltip="row.showOverflowTooltip"></el-table-column>\n' +
        '                        </template>\n' +
        '                        <el-table-column fixed="right" label="操作" width="100">\n' +
        '                            <template  slot-scope="scope">\n' +
        '                                <el-button v-if="operate === operateSelect[0]" @click="handleClick(scope.row)" type="text" size="small">查看</el-button>\n' +
        '                                <el-button v-if="operate === operateSelect[0]" type="text" size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>\n' +
        '                                <el-button v-if="operate === operateSelect[1]" size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>\n' +
        '                            </template>\n' +
        '                        </el-table-column>\n' +
        '                    </el-table>'
};

export default tableCom;