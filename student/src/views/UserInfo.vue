<template>
  <div style="background: #f0f2f5;height: 900px">
    <navigation></navigation>
    <div style="width: 70%;margin-left: 15%;margin-top: 1%">
      <el-row class="tac">
        <el-col :span="12" style="width: 30%">
          <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose">
            <el-menu-item index="1">
              <i class="el-icon-menu"></i>
              <span slot="title">个人信息</span>
            </el-menu-item>
          </el-menu>
        </el-col>
        <el-col style="width: 69%;margin-left: 1%">
          <h1 style="float: left;margin-top: 1%">基本设置</h1>
          <div style="margin-top: 10%">
            <p style="margin-top: 1%;float: left">姓名</p>
            <el-input></el-input>
            <p style="float: left;margin-top: 1%">邮箱</p>
            <el-input></el-input>
            <el-button style="margin-top: 1%">保存</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <Footer style="margin-top: 20%"></Footer>
  </div>
</template>

<script>
import Navigation from "@/views/Navigation";
import Footer from "@/views/Footer";
export default {
  name: "UserInfo",
  components: {Footer, Navigation}
}
</script>

<style scoped>

</style>