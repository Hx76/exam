<template>
  <div>
    <el-menu
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#00aeff"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item index="1" class="logo"></el-menu-item>
      <el-menu-item index="2">
        <template slot="title">第一次考试</template>
      </el-menu-item>
      <el-menu-item index="3" style="float: right"><el-button >交卷</el-button></el-menu-item>
      <el-menu-item index="4" style="float: right">
        <el-button type="text" style="color: #e4f0ef">倒计时还有90分钟0秒</el-button>
      </el-menu-item>
    </el-menu>
    <el-container style="border: 0px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu router :default-openeds="['1','2','3']">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>选择题</span>
            </template>
            <el-menu-item v-for="(item2,index1) in this.selectionQuestionNumber" @onclick="test()">
              第{{item2.id}}题
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>判断题</span>
            </template>
            <el-menu-item v-for="(item2,index2) in this.judgeQuestionNumber">
              第{{item2.id}}题
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>填空题</span>
            </template>
            <el-menu-item v-for="(item2,index3) in this.fillingQuestionNumber">
              第{{item2.id}}题
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <div style="float: left;margin-left: 5%;font-size: 30px" >
        <h4>单选题</h4>
        <label>1+1=?</label>
        <br>
        <el-radio-group style="margin-top: 10%;font-size: 30px">
          <el-radio v-for="(item2,index1) in this.selectionQuestionNumber">
            <h2>A.</h2>
            <h2>1</h2>
            <br>
          </el-radio>
        </el-radio-group>
      </div>
      </el-container>
  </div>
</template>

<script>
export default {
  name: "Exam",
  created() {
    this.exam_id = this.$route.params.examId
    console.log(this.exam_id)
  },
  data() {
    return {
      exam_id: 0,
      flug: 'none',
      description: '这是考试，',
      selectionQuestionNumber: [
        {id: 1},
        {id: 2}],
      judgeQuestionNumber: [
        {id: 1},
        {id: 2}],
      fillingQuestionNumber: [
        {id: 1},
        {id: 2}],
      desc: '距离考试开始还有10：'
    };
  },
  methods:{
    test(){

    }
  }
}
</script>

<style scoped>

</style>