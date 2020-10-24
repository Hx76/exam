<template>
  <div style="width: 70%;margin-left: 15%" >
    <el-row style="margin-top: 1%">
      <el-col :span="2" v-for="(o, index) in 9" :key="o" :offset="index > 0 ? 3 : 0" style="width: 32%;margin-left: 1%">
        <el-card class="box-card" style="margin-top: 5%;width: 100%">
          <div slot="header" class="clearfix">
            <span>考试名称</span>
            <br/>
            <span>考试id</span>
          </div>
          <span>考试开始时间：2020/10/1</span>
          <br/>
          <span>考试时间：90分钟</span>
          <br/>
          <el-button type="text" @click="dialogVisible=true">加入考试</el-button>
          <el-dialog
              title="提示"
              :visible.sync="dialogVisible"
              width="30%"
              append-to-body
              :before-close="handleClose">
            <el-form :model="form">
              <el-form-item label="请输入考试邀请码:">
                <el-input v-model="key" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="goToExam">确 定</el-button>
  </span>
          </el-dialog>
        </el-card>
      </el-col>
    </el-row>
    <el-pagination style="margin-top: 2%"
                   background
                   layout="prev, pager, next"
                   page-size=9
                   :total="total"
                   @current-change="page"
    >
    </el-pagination>
  </div>

</template>

<script>
import Navigation from "@/views/Navigation";
export default {
  name: "ExamList",
  components: {Navigation},
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
      currentDate: new Date(),
      total: 0,
      dialogVisible: false,
      key: '',
      examInfo: [
        {
          id: 0,
        }
      ]
    };
  },
  methods:{
    goToExam(){
      if (this.key==='123456'){
        this.$router.replace('/exam')
      }else {
        alert("邀请码错误");
      }
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    }
  }
}
</script>

<style scoped>

</style>