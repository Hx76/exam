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
        <template slot="title">第一次考试</template>
      </el-menu-item>
      <el-menu-item index="3" style="float: right"><el-button >交卷</el-button></el-menu-item>
      <el-menu-item index="4" style="float: right">
        <el-button type="text" style="color: #e4f0ef">倒计时还有90分钟0秒</el-button>
      </el-menu-item>
    </el-menu>
    <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        style="float: left">
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>选择题</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1">题目1</el-menu-item>
          <el-menu-item index="1-2">题目2</el-menu-item>
          <el-menu-item index="1-3">题目3</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>判断题</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1">题目1</el-menu-item>
          <el-menu-item index="1-2">题目2</el-menu-item>
          <el-menu-item index="1-3">题目3</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>填空题</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="1-1">题目1</el-menu-item>
          <el-menu-item index="1-2">题目2</el-menu-item>
          <el-menu-item index="1-3">题目3</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Exam"
}
</script>

<style scoped>

</style>