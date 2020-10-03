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
          <el-button type="text">加入考试</el-button>
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
export default {
  name: "ExamList",
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
      examInfo: [
        {

        }
      ]
    };
  }
}
</script>

<style scoped>

</style>