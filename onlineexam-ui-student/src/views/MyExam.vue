<template>
  <div>
    <Navigation></Navigation>
    <br>
    <div style="width: 70%;margin-left: 15%;margin-top: 1%;background: #fff;height: 900px">

      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column
            prop="exam_id"
            label="考试编号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="sum_score"
            label="总分">
        </el-table-column>
        <el-table-column
            prop="score"
            label="成绩">
        </el-table-column>
        <el-table-column
            label="操作">
          <template slot-scope="scope">
          <el-button type="text" @click="goToExamPaper(scope.row)">详情</el-button>
          </template>
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
  name: "MyExam",
  components: {Footer, Navigation},
  created() {
    this.userInfo.email = this.$route.params.email
    this.userInfo.userName = this.$route.params.userName
    const _this = this
    axios.get('http://localhost:82/exam/countMyJoinExam/'+this.userInfo.email).then(function (resp) {
      _this.total = resp.data['data']
      console.log(resp.data)
    })
    axios.get('http://localhost:82/exam/showMyJoinExam/1/8/'+this.userInfo.email).then(function (resp) {
      _this.tableData = resp.data['data']
    })
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
      tableData: [{
        exam_id: '',
        score: '',
        sum_score: '',
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
      axios.get('http://localhost:82/exam/countMyJoinExam/'+this.userInfo.email).then(function (resp) {
        _this.total = resp.data['data']
        console.log(resp.data)
      })
      axios.get('http://localhost:82/exam/showMyJoinExam/'+currentPage+'/8/'+this.userInfo.email).then(function (resp) {
        _this.tableData = resp.data['data']
      })
    },
    deleteQuestion(){
      this.centerDialogVisible = false
    },
    goToExamPaper(e){
      var rowInfo = JSON.stringify(e);
      var json = eval('(' + rowInfo + ')');
      console.log(json)
      this.exam_id = json.exam_id
      console.log(this.exam_id)
      this.$router.push({
        path: "/examPaper",
        name: "examPaper",
        params: {email: this.userInfo.email,exam_id: this.exam_id}
      });
      _this.$router.replace('/examPaper')
    }
  }
}
</script>

<style scoped>

</style>