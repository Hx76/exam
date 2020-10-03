<template>
  <div>
  <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#00aeff"
      text-color="#fff"
      active-text-color="#ffd04b">

    <el-menu-item index="1"></el-menu-item>
    <el-menu-item index="2">
      <template slot="title">线上考试系统</template>
    </el-menu-item>
    <el-menu-item index="3" @click="goToExamManage()">考试管理</el-menu-item>
    <el-menu-item index="4" @click="goToQuestionManage()">问题管理</el-menu-item>
    <el-menu-item index="5" @click="goToMyExam()">我的考试</el-menu-item>
    <el-submenu index="6" style="float: right">
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
    const _this = this
    axios.post('http://localhost:80/login/getUser').then(function (resp) {
      _this.userInfo.userName = resp.data['data']
    })
  },
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '2',
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
      this.$router.replace('/examManage')
    },
    goToQuestionManage(){
      this.$router.replace('/questionManage')
    },
    goToMyExam(){
      this.$router.replace('/myExam')
    }
  }
}
</script>

<style scoped>

</style>