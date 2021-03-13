<template>
  <div>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#00aeff"
      text-color="#fff"
      active-text-color="#ffd04b">

    <el-menu-item index="1"></el-menu-item>
    <el-menu-item index="2" @click="goToExamList()">
      <template slot="title">线上考试系统</template>
    </el-menu-item>
    <el-menu-item index="3" @click="goToExamManage()">考试管理</el-menu-item>
    <el-menu-item index="4" @click="goToQuestionManage()">问题管理</el-menu-item>
    <el-menu-item index="5" @click="goToMyExam()">我的考试</el-menu-item>
    <el-menu-item index="6" @click="goToMyCreatedExam()">我发布的考试</el-menu-item>
    <el-submenu index="7" style="float: right">
      <template slot="title">{{userInfo.userName}}</template>
      <el-menu-item index="2-1" @click="goToUserInfo()">设置</el-menu-item>
      <el-menu-item index="2-2" @click="logout()">登出</el-menu-item>
    </el-submenu>
  </el-menu>
  </div>
</template>

<script>
export default {
  name: "Navigation",
  created() {
    this.activeIndex=this.$route.params.activeIndex
    const _this = this
    axios.post('http://localhost:82/exam/getUser').then(function (resp) {
      _this.userInfo.userName = resp.data['data']
    })
  },
  data() {
    return {
      activeIndex: '2',
      userInfo: {
        userName: '用户',
        email: '邮箱'
      }
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    goToUserInfo(){
      this.$router.push({
        path: "/userInfo",
        name: "userInfo",
        params: { username: this.userInfo.userName,email: this.userInfo.email}
      });
      this.$router.replace('/userInfo')
    },
    logout(){
      axios.post('http://localhost:80/logout').then(function (resp) {})
      this.$router.replace('/')
    },
    goToExamManage(){
      this.$router.push({
        path: "/examManage",
        name: "examManage",
        params: { activeIndex: '3'}
      });
      this.$router.replace('/examManage')

    },
    goToQuestionManage(){
      this.$router.push({
        path: "/questionManage",
        name: "questionManage",
        params: { activeIndex: '4'}
      });
      this.$router.replace('/questionManage')
    },
    goToMyExam(){
      this.$router.push({
        path: "/myExam",
        name: "myExam",
        params: { activeIndex: '5'}
      });
      this.$router.replace('/myExam')
    },
    goToExamList(){
      this.$router.push({
        path: "/home",
        name: "home",
        params: { activeIndex: '2'}
      });
      this.$router.replace('/home')
    },
    goToMyCreatedExam(){
      this.$router.push({
        path: "/myCreatedExam",
        name: "myCreatedExam",
        params: { activeIndex: '6'}
      });
      this.$router.replace('/myCreatedExam')
    }
  }
}
</script>

<style scoped>

</style>