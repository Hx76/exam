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

    <el-menu-item index="1" class="logo"></el-menu-item>
    <el-menu-item index="2">
      <template slot="title">线上考试系统</template>
    </el-menu-item>
    <el-menu-item index="3">考试管理</el-menu-item>
    <el-menu-item index="4">问题管理</el-menu-item>
    <el-menu-item index="5">我的考试</el-menu-item>
    <el-submenu index="6" style="float: right">
      <template slot="title">hx76</template>
      <el-menu-item index="2-1" @click="goToUserInfo()">设置</el-menu-item>
      <el-menu-item index="2-2">登出</el-menu-item>
    </el-submenu>
  </el-menu>
  </div>
</template>

<script>
export default {
  name: "Navigation",
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    goToUserInfo(){
      this.$router.replace('/userInfo')
    }
  }
}
</script>

<style scoped>

</style>