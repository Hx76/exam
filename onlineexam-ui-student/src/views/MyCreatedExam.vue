<template>
  <div>
    <Navigation></Navigation>
    <br>
    <div style="width: 70%;margin-left: 15%;margin-top: 1%;background: #fff;height: 900px">
      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column
            prop="exam_serial_number"
            label="邀请码"
            width="80">
        </el-table-column>
        <el-table-column
            prop="name"
            label="考试名称"
            width="180">
        </el-table-column>
        <el-table-column
            prop="state"
            label="状态">
        </el-table-column>
        <el-table-column
            prop="number_of_people"
            label="参加人数">
        </el-table-column>
        <el-table-column
            label="操作">
          <template slot-scope="scope">
          <el-button type="text" @click="viewReport(scope.row)">详情</el-button>
          <el-dialog
              title="考试详情"
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
                :data="tableData1"
                style="width: 100%">
              <el-table-column
                  prop="rank"
                  label="名次"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="name"
                  label="用户名"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="email"
                  label="邮箱">
              </el-table-column>
              <el-table-column
                  prop="score"
                  label="分数">
              </el-table-column>
              <el-table-column
                  label="操作">
                <template slot-scope="scope">
                  <el-button type="text" @click="viewExamPaper(scope.row)">查看试卷</el-button>
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
              append-to-body
          >
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
    <Footer/>
  </div>
</template>

<script>
import Footer from "@/views/Footer";
import Navigation from "@/views/Navigation";

export default {
  name: "MyCreatedExam",
  components: {Footer, Navigation},
  created() {
    this.userInfo.email = this.$route.params.email
    this.userInfo.userName = this.$route.params.userName
    const _this = this
    axios.get('http://localhost:9527/exam/countMyCreatedExam/'+_this.userInfo.email,
        {
          params: {
            token: sessionStorage.getItem('token')
          }
        }).then(function (resp) {
      _this.total = resp.data['data']
      console.log(resp.data)
    })
    axios.get('http://localhost:9527/exam/showMyCreatedExam/1/8/'+_this.userInfo.email,
        {
          params: {
            token: sessionStorage.getItem('token')
          }
        }).then(function (resp) {
      _this.tableData = resp.data['data']
    })
    console.log("用户名用户名啊用户名："+this.userInfo.userName)
  },
  data() {
    return {
      userInfo: {
        userName: '用户',
        email: '',
      },
      updateDialogVisible: false,
      dialogVisible: false,
      centerDialogVisible: false,
      examNumber: 0,
      active: 0,
      total: 0,
      options: [{
        value: '选项1',
        label: '选择题'
      }, {
        value: '选项2',
        label: '判断题'
      }, {
        value: '选项3',
        label: '填空题'
      }],
      value: '',
      formData: [{
        question_body: '',
        score: '',
        creator: '',
        type_id: '',
        option: [],
        answer: ''
      }],
      dialogData:[{
        question_body: '1',
        score: '5',
        creator: '4',
        type_id: '5',
        option: [],
        answer: '7'
      }],
      tableData1: [{
        rank: '',
        name: '',
        email: '',
        score: '',
      }],
      tableData: [{
        exam_serial_number: '',
        name: '',
        state: '',
        number_of_people: '',
      }]
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
      axios.get('http://localhost:9527/exam/countMyCreatedExam/'+_this.userInfo.email,
          {
            params: {
              token: sessionStorage.getItem('token')
            }
          }).then(function (resp) {
        _this.total = resp.data['data']
        console.log(resp.data)
      })
      axios.get('http://localhost:9527/exam/showMyCreatedExam/'+currentPage+'/8/'+_this.userInfo.email,
          {
            params: {
              token: sessionStorage.getItem('token')
            }
          }).then(function (resp) {
        _this.tableData = resp.data['data']
      })
    },
    deleteQuestion(){
      this.centerDialogVisible = false
    },
    viewReport(e){
      this.updateDialogVisible = true
      var rowInfo = JSON.stringify(e);
      var json = eval('(' + rowInfo + ')');
      console.log("邀请码"+json.exam_serial_number)
      this.examNumber=json.exam_serial_number
      const _this = this
      axios.get('http://localhost:85/score/getReport/'+json.exam_serial_number).then(function (resp) {
        _this.tableData1 = resp.data['data']
        console.log(resp.data)
      })
    },
    viewExamPaper(e){
      var rowInfo = JSON.stringify(e);
      var json = eval('(' + rowInfo + ')');
      this.$router.push({
        path: "/examPaper",
        name: "examPaper",
        params: {email: this.userInfo.email,
          email1: json.email,
          userName: this.userInfo.userName,
          exam_id: this.examNumber}
      });
      _this.$router.replace('/examPaper')
    }
  }
}
</script>

<style scoped>

</style>