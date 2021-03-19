<template>
  <div>
    <Navigation></Navigation>
    <div style="width: 70%;margin-left: 15%;margin-top: 1%;background: #fff;height: 900px">
      <label style="width: 10%;margin-top: 2%;margin-left: 5%">考试编号：</label>
      <el-input style="width: 37%;margin-top: 2%;margin-left: 1%"></el-input>
      <el-button style="width: 10%;margin-left: 1%">搜索</el-button>
      <el-divider style="margin-top: 1%"></el-divider>
      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column
            prop="question_body"
            label="考试名称"
            width="180">
        </el-table-column>
        <el-table-column
            prop="score"
            label="开始时间">
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
            label="成绩">
        </el-table-column>
        <el-table-column
            prop="type_id"
            label="参加人数">
        </el-table-column>
        <el-table-column
            prop="type_id"
            label="名次">
        </el-table-column>
        <el-table-column
            label="操作">
          <el-button type="text" @click="updateDialogVisible = true">详情</el-button>
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
    const _this = this
    axios.get('http://localhost:84/question/countAll').then(function (resp) {
      _this.total = resp.data['data']
      console.log(resp.data)
    })
    axios.get('http://localhost:84/question/showAll/1/8').then(function (resp) {
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
        id: '',
        question_body: '',
        score: '',
        creator: '',
        type_id: '',
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
      axios.get('http://localhost:84/question/countAll').then(function (resp) {
        _this.total = resp.data['data']
        console.log(resp.data)
      })
      axios.get('http://localhost:84/question/showAll/' + currentPage + '/8').then(function (resp) {
        _this.tableData = resp.data['data']

        if (resp.data['data']['type_id'] === 1) {

          _this.tableData.type_id = '选择题'
        } else if (_this.tableData.type_id === 2) {
          _this.tableData.type_id = '判断题'
        } else {
          _this.tableData.type_id = '填空题'
        }
      })
    },
    deleteQuestion(){
      this.centerDialogVisible = false
    }
  }
}
</script>

<style scoped>

</style>