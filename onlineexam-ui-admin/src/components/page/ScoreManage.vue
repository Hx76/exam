<template>
  <div style="background: #f0f2f5;">
    <div style="width: 70%;margin-left: 15%;margin-top: 1%;background: #fff;height: 900px">
      <label style="width: 10%;margin-top: 2%;margin-left: 5%">考试id：</label>
      <el-input style="width: 37%;margin-top: 2%;margin-left: 1%"></el-input>
      <el-button style="width: 10%;margin-left: 1%">搜索</el-button>

      <el-divider style="margin-top: 1%"></el-divider>
      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column
            type="selection">
          <el-checkbox></el-checkbox>
        </el-table-column>
        <el-table-column
            prop="id"
            label="id"
            width="180">
        </el-table-column>
        <el-table-column
            prop="question_body"
            label="考试名称"
            width="180">
        </el-table-column>
        <el-table-column
            prop="score"
            label="考试时间">
        </el-table-column>
        <el-table-column
            prop="creator"
            label="创建人">
        </el-table-column>
        <el-table-column
            prop="type_id"
            label="总分">
        </el-table-column>
        <el-table-column
            prop="type_id"
            label="参加人数">
        </el-table-column>
        <el-table-column
            label="操作">
          <template slot-scope="scope">
            <el-button type="text" @click="updateQuestion(scope.row)">详情</el-button>
            <el-dialog
                title="题目详情"
                :visible.sync="updateDialogVisible"
                width="80%"
                :before-close="handleClose">
              <div>
                <el-form :model="dialogData">
                  <el-form-item label="成绩单:">
                  </el-form-item>
                </el-form>
              </div>
              <el-table
                  :data="tableData"
                  style="width: 100%">
                <el-table-column
                    type="selection">
                  <el-checkbox></el-checkbox>
                </el-table-column>
                <el-table-column
                    prop="id"
                    label="名次"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="question_body"
                    label="用户名"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="score"
                    label="邮箱">
                </el-table-column>
                <el-table-column
                    prop="creator"
                    label="分数">
                </el-table-column>
                <el-table-column
                    label="操作">
                  <template slot-scope="scope">
                    <el-button type="text" @click="updateQuestion(scope.row)">查看试卷</el-button>
                  </template>
                </el-table-column>
              </el-table>


              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = true">确 定</el-button>
              </span>
            </el-dialog>
            <el-button type="text" @click="centerDialogVisible = true">删除</el-button>
          </template>
          <el-dialog
              title="提示"
              :visible.sync="centerDialogVisible"
              width="30%"
              append-to-body>
            <span>确定要删除该条数据吗？</span>
            <span slot="footer" class="dialog-footer">
              <el-button @click="centerDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="deleteQuestion()">确 定</el-button>
             </span>
          </el-dialog>
        </el-table-column>
      </el-table>
      <el-pagination style="margin-top: 2%"
                     background
                     layout="prev, pager, next"
                     page-size=8
                     :total="total"
                     @current-change="page">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "ScoreManage",
  created() {
    console.log("邮箱："+this.userInfo.email)
    console.log(this.userInfo.userName)
    const _this = this
    axios.get('http://localhost:9527/question/countAll',
        {
          params:{
            token: sessionStorage.getItem('token')
          }
        }).then(function (resp) {
      if (resp.data['message']!=="没有权限"){
        console.log(resp.data['message'])
        _this.total = resp.data['data']
      }else {
        alert("没有权限！")
      }

      console.log(resp.data)
    })
    axios.get('http://localhost:9527/question/showAll/1/8',
        {
          params:{
            token: sessionStorage.getItem('token')
          }
        }).then(function (resp) {
      _this.tableData = resp.data['data']
    })
  },
  data() {
    return {
      userInfo: {
        userName: '用户',
        email: '',
      },
      formLabelWidth: 10,
      test: true,
      updateDialogVisible: false,
      dialogVisible: false,
      centerDialogVisible: false,
      active: 0,
      total: 0,
      option_number: 0,
      options: [{
        value: '选择题',
        label: '选择题'
      }, {
        value: '判断题',
        label: '判断题'
      }, {
        value: '填空题',
        label: '填空题'
      }],
      answer_options: [{
        value: '选项1',
        label: '选项1',
        disabled: false
      }, {
        value: '选项2',
        label: '选项2',
        disabled: false
      }, {
        value: '选项3',
        label: '选项3',
        disabled: false
      },{
        value: '选项4',
        label: '选项4',
        disabled: false
      }],
      value: '',
      formData: [{
        question_body: '',
        score: '',
        creator: '',
        type_id: '',
      }],
      tableData: [{
        id: '',
        question_body: '',
        score: '',
        creator: '',
        type_id: '',
      }],
      dialogData:{
        body: '123456',
        type: '',
        option: [{
          value: '选项1',
          option_id:'',
          option_body: '',
          disable: false
        },
          {
            value: '选项2',
            option_id:'',
            option_body: '',
            disable: false
          },
          {
            value: '选项3',
            option_id:'',
            option_body: '',
            disable: false
          },
          {
            value: '选项4',
            option_id:'',
            option_body: '',
            disable: false
          }],
        answers: '',
        scores: ''
      }
    }
  },
  watch: {
    'dialogData.answers'(val,oldVal){
      if (val!==""){
        if (this.dialogData.type===""){
          alert("请先选择题目类型！");
          this.dialogData.answers = "";
        }
      }
    },
    'dialogData.type'(val, oldVal) {
      console.log("inputVal = " + val + " , oldValue = " + oldVal)
      if (val==='判断题'){
        this.dialogData.option[0].option_body="对"
        this.dialogData.option[1].option_body="错"
        this.dialogData.option[1].disable=false;
        this.dialogData.option[2].disable=true;
        this.dialogData.option[3].disable=true;
        this.answer_options[2].disabled=true
        this.answer_options[3].disabled=true
        this.answer_options[1].disabled=false
      }else if(val==='选择题'){
        this.dialogData.body = '456'
        this.dialogData.option[0].option_body=""
        this.dialogData.option[1].option_body=""
        this.dialogData.option[2].disable=false;
        this.dialogData.option[3].disable=false;
        this.dialogData.option[0].disable=false;
        this.dialogData.option[1].disable=false;
        this.answer_options[2].disabled=false
        this.answer_options[3].disabled=false
        this.answer_options[1].disabled=false
        this.answer_options[0].disabled=false
      }else{
        this.dialogData.body = '789'
        this.dialogData.option[0].option_body=""
        this.dialogData.option[1].option_body=""
        this.dialogData.option[1].disable=true;
        this.dialogData.option[2].disable=true;
        this.dialogData.option[3].disable=true;
        this.answer_options[2].disabled=true
        this.answer_options[3].disabled=true
        this.answer_options[1].disabled=true
        this.answer_options[0].disabled=true
      }
    }
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
    page(currentPage) {
      const _this = this
      axios.get('http://localhost:84/question/countAll').then(function (resp) {
        _this.total = resp.data['data']
        console.log("zhelizheli"+resp.data)
      })
      axios.get('http://localhost:84/question/showAll/' + currentPage + '/8').then(function (resp) {
        _this.tableData = resp.data['data']

      })
    },
    deleteQuestion(){
      this.centerDialogVisible = false
    },
    updateQuestion(e){
      this.updateDialogVisible = true
      var rowInfo = JSON.stringify(e);
      var json = eval('(' + rowInfo + ')');
      console.log(json.question_body)
      this.dialogData.body=json.question_body;
      this.dialogData.type=json.type_id;
      this.dialogData.scores=json.score;
    },
    createQuestion(){

    }

  }
}
</script>

<style scoped>

</style>
