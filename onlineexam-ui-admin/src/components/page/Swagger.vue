<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 接口文档
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-tabs type="border-card">
<!--        <el-tab-pane label="登录模块">-->
<!--          <iframe src="http://localhost:80/swagger-ui.html" style="height: 800px;width: 100%"></iframe>-->
<!--        </el-tab-pane>-->
<!--        <el-tab-pane label="注册模块">-->
<!--          <iframe src="http://localhost:81/swagger-ui.html" style="height: 800px;width: 100%"></iframe>-->
<!--        </el-tab-pane>-->
        <el-tab-pane label="个人信息模块">
          <iframe src="http://localhost:83/swagger-ui.html" style="height: 800px;width: 100%"></iframe>
        </el-tab-pane>
<!--        <el-tab-pane label="考试模块">-->
<!--          <iframe src="http://localhost:82/swagger-ui.html" style="height: 800px;width: 100%"></iframe>-->
<!--        </el-tab-pane>-->
<!--        <el-tab-pane label="成绩模块">-->
<!--          <iframe src="http://localhost:85/swagger-ui.html" style="height: 800px;width: 100%"></iframe>-->
<!--        </el-tab-pane>-->
<!--        <el-tab-pane label="问题模块">-->
<!--          <iframe src="http://localhost:84/swagger-ui.html" style="height: 800px;width: 100%"></iframe>-->
<!--        </el-tab-pane>-->
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  name: "Swagger"
}
</script>

<style scoped>

</style>